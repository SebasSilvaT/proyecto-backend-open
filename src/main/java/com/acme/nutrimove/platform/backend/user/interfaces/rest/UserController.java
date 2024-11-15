package com.acme.nutrimove.platform.backend.user.interfaces.rest;

import com.acme.nutrimove.platform.backend.user.domain.model.aggregates.User;
import com.acme.nutrimove.platform.backend.user.domain.model.commands.DeleteUserCommand;
import com.acme.nutrimove.platform.backend.user.domain.model.queries.GetUserByIdQuery;
import com.acme.nutrimove.platform.backend.user.domain.model.queries.GetAllUsersByEmailQuery;
import com.acme.nutrimove.platform.backend.user.domain.services.UserCommandService;
import com.acme.nutrimove.platform.backend.user.domain.services.UserQueryService;
import com.acme.nutrimove.platform.backend.user.interfaces.rest.resources.UpdateUserResource;
import com.acme.nutrimove.platform.backend.user.interfaces.rest.resources.UserResource;
import com.acme.nutrimove.platform.backend.user.interfaces.rest.resources.CreateUserResource;
import com.acme.nutrimove.platform.backend.user.interfaces.rest.transform.UpdateUserCommandFromResourceAssembler;
import com.acme.nutrimove.platform.backend.user.interfaces.rest.transform.UserResourceFromEntityAssembler;
import com.acme.nutrimove.platform.backend.user.interfaces.rest.transform.CreateUserCommandFromResourceAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "api/v1/users", produces = APPLICATION_JSON_VALUE)
@Tag(name = "Users", description = "Operations related to Users")
public class UserController {
    private final UserQueryService userQueryService;
    private final UserCommandService userCommandService;

    public UserController(UserQueryService userQueryService, UserCommandService userCommandService) {
        this.userQueryService = userQueryService;
        this.userCommandService = userCommandService;
    }

    @Operation(summary = "Create a user", description = "Create a user with the provided details")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User created"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
    })
    @PostMapping
    public ResponseEntity<UserResource> createUser(@RequestBody CreateUserResource resource) {
        Optional<User> user = userCommandService
                .handle(CreateUserCommandFromResourceAssembler.toCommand(resource));
        return user.map(createdUser -> new ResponseEntity<>(UserResourceFromEntityAssembler.toResourceFromEntity(createdUser), CREATED))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @GetMapping("email/{email}")
    public ResponseEntity<List<UserResource>> getAllUsersByEmail(@PathVariable String email) {
        var getAllUsersByEmailQuery = new GetAllUsersByEmailQuery(email);
        var users = userQueryService.handle(getAllUsersByEmailQuery);
        if (users.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var userResources = users.stream()
                .map(UserResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(userResources);
    }

    @GetMapping("{id}")
    public ResponseEntity<UserResource> getUserById(@PathVariable Long id) {
        Optional<User> user = userQueryService.handle(new GetUserByIdQuery(id));
        return user.map(foundUser -> ResponseEntity.ok(UserResourceFromEntityAssembler.toResourceFromEntity(foundUser)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("")
    public ResponseEntity<List<UserResource>> getAllUsers() {
        var users = userQueryService.getAllUsers();
        if (users.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var userResources = users.stream()
                .map(UserResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(userResources);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update user", description = "Update user information by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User updated"),
            @ApiResponse(responseCode = "404", description = "User not found"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
    })
    public ResponseEntity<UserResource> updateUser(@PathVariable Long id, @RequestBody UpdateUserResource resource) {
        var updateUserCommand = UpdateUserCommandFromResourceAssembler.toCommand(id, resource);
        var updatedUser = userCommandService.handle(updateUserCommand);
        return updatedUser.map(user -> ResponseEntity.ok(UserResourceFromEntityAssembler.toResourceFromEntity(user)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


    @Operation(summary = "Delete a user", description = "Delete a user by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "User deleted"),
            @ApiResponse(responseCode = "404", description = "User not found"),
    })
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        Optional<User> user = userQueryService.handle(new GetUserByIdQuery(id));
        if (user.isPresent()) {
            userCommandService.handle(new DeleteUserCommand(id));
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
