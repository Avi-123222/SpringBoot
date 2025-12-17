package com.tastytown.backend.controller;

import com.tastytown.backend.constants.OrderStatus;
import com.tastytown.backend.dto.BillingInfoDTO;
import com.tastytown.backend.dto.OrderDTO;
import com.tastytown.backend.service.IOrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Order API", description = "A controller for managing user and system orders.")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/orders")
@SecurityRequirement(name = "bearerAuth")
public class OrderController {
    private final IOrderService orderService;

    /**
     * Places a new order for the authenticated user.
     * @param userId The ID of the authenticated user (from RequestAttribute).
     * @param billingInfo The billing and shipping information for the order.
     * @return The newly created OrderDTO.
     */
    @Operation(
            summary = "Place a new order",
            description = "Creates a new order using the authenticated user's ID and provided billing information.",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Order placed successfully",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = OrderDTO.class))
                    ),
                    @ApiResponse(responseCode = "400", description = "Invalid billing information"),
                    @ApiResponse(responseCode = "401", description = "Unauthorized - Missing or invalid token")
            }
    )
    @PostMapping
    public ResponseEntity<OrderDTO> placeOrder(
            @Parameter(hidden = true) @RequestAttribute String userId,
            @RequestBody BillingInfoDTO billingInfo) {
        return new ResponseEntity<>(orderService.createOrder(userId, billingInfo), HttpStatus.CREATED);
    }

    /**
     * Retrieves all orders for the authenticated user.
     * @param userId The ID of the authenticated user (from RequestAttribute).
     * @return A list of orders belonging to the user.
     */
    @Operation(
            summary = "Get all orders of the authenticated user",
            description = "Retrieves a list of all orders placed by the user identified by the security context.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Orders retrieved successfully",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = OrderDTO.class))
                    ),
                    @ApiResponse(responseCode = "401", description = "Unauthorized - Missing or invalid token")
            }
    )
    @GetMapping("/user")
    public ResponseEntity<List<OrderDTO>> getOrdersOfAnUser(
            @Parameter(hidden = true) @RequestAttribute String userId) {
        return ResponseEntity.ok(orderService.getOrdersOfAnUser(userId));
    }

    /**
     * Retrieves all orders across all users (Admin access required).
     * @param userId The ID of the authenticated user for authorization check.
     * @return A list of all orders in the system.
     */
    @Operation(
            summary = "Get all orders in the system (Admin only)",
            description = "Retrieves a list of all orders placed by all users. Requires administrator privileges.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "All orders retrieved successfully",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = OrderDTO.class))
                    ),
                    @ApiResponse(responseCode = "401", description = "Unauthorized - Missing or invalid token"),
                    @ApiResponse(responseCode = "403", description = "Forbidden - User does not have admin privileges")
            }
    )
    @GetMapping
    public ResponseEntity<List<OrderDTO>> getAllOrders(
            @Parameter(hidden = true) @RequestAttribute String userId) {
        return ResponseEntity.ok(orderService.getAllOrders(userId));
    }

    /**
     * Updates the status of a specific order (Admin/Staff access required).
     * @param orderId The ID of the order to update.
     * @param status The new status of the order (e.g., PLACED, SHIPPED, DELIVERED).
     * @return The updated OrderDTO.
     */
    @Operation(
            summary = "Update order status by ID (Admin/Staff only)",
            description = "Updates the status of a specific order. Typically used by admin or staff.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Order status updated successfully",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = OrderDTO.class))
                    ),
                    @ApiResponse(responseCode = "404", description = "Order not found"),
                    @ApiResponse(responseCode = "401", description = "Unauthorized - Missing or invalid token"),
                    @ApiResponse(responseCode = "403", description = "Forbidden - User does not have necessary privileges")
            }
    )
    @PutMapping("/{orderId}/status")
    public ResponseEntity<OrderDTO> updateOrderStatus(
            @Parameter(description = "The unique identifier of the order") @PathVariable String orderId,
            @Parameter(description = "The new status for the order") @RequestParam OrderStatus status) {
        return ResponseEntity.ok(orderService.updateOrderStatus(orderId, status));
    }
}