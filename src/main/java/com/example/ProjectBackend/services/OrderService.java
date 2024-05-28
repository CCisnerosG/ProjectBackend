package com.example.ProjectBackend.services;

import com.example.ProjectBackend.dtos.OrderAndProductsDto;
import com.example.ProjectBackend.dtos.OrderProductDto;
import com.example.ProjectBackend.entities.*;
import com.example.ProjectBackend.exceptions.PokemonNotFoundException;
import com.example.ProjectBackend.exceptions.UserNotFoundException;
import com.example.ProjectBackend.repositories.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderProductRepository orderProductRepository;

    @Autowired
    private PokemonRepository pokemonRepository;

    @Autowired
    private StateRepository stateRepository;

    @Autowired
    private ShoppingCartService shoppingCartService;

    @Autowired
    private ShoppingCartRepository shoppingCartRepository;

    @Autowired
    private ShoppingCartProductRepository shoppingCartProductRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    public List<OrderAndProductsDto> allOrders(){
        List<Order> orders = orderRepository.findAll();

        return orders.stream().map(order -> {
            OrderAndProductsDto dto = modelMapper.map(order, OrderAndProductsDto.class);
            List<OrderProduct> orderProducts = orderProductRepository.findByOrder(order);

            List<OrderProductDto> productDtos = orderProducts.stream()
                    .map(orderProduct -> modelMapper.map(orderProduct, OrderProductDto.class))
                    .collect(Collectors.toList());

            dto.setProducts(productDtos);

            return dto;
        }).collect(Collectors.toList());
    }

    public void updateState(Long orderId, Integer stateId){
        Optional<Order> orderOptional = orderRepository.findById(orderId);
        State state = stateRepository.findById(stateId).orElse(null);
        if (state == null) {
            throw new RuntimeException("State not found");
        }
        if (orderOptional.isPresent()) {
            Order order = orderOptional.get();
            order.setState(state);
            orderRepository.save(order);
        } else {
            throw new RuntimeException("Order not found");
        }
    }

    @Transactional
    public void fromCartToOrder(Long userId, String address, String paymentMethod) {
        ShoppingCart shoppingCart = shoppingCartRepository.findByUserId(userId);
        List<ShoppingCartProduct> shoppingCartProducts = shoppingCartService.getAllProducts(userId);

        Order order = new Order();
        User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        order.setUser(user);
        State pendingState = stateRepository.findByName(StateEnum.PENDING)
                .orElseThrow(() -> new RuntimeException("State 'PENDING' not found"));
        order.setState(pendingState);
        order.setTotal(0);
        order.setAddress(setAddress(address));
        order.setPaymentMethod(setPaymentMethod(paymentMethod));

        orderRepository.save(order);

        for (ShoppingCartProduct product : shoppingCartProducts) {
            addToOrder(order, product.getPokemon().getId(), product.getQuantity());
        }

        shoppingCartService.emptyShoppingCart(shoppingCart.getId());

    }

    public void addToOrder(Order order, Integer pokemonId, Integer quantity) {
        OrderProduct orderProduct = orderProductRepository.findByOrderIdAndPokemonId(order.getId(), pokemonId);
        if (orderProduct == null) {
            orderProduct = new OrderProduct();
            orderProduct.setOrder(order);
            Pokemon pokemon = pokemonRepository.findById(pokemonId).orElseThrow(PokemonNotFoundException::new);
            orderProduct.setPokemon(pokemon);
            orderProduct.setPrice(pokemon.getPrice());
            orderProduct.setSubtotal(pokemon.getSubtotal());
            orderProduct.setSave(pokemon.getSave());
            orderProduct.setTaxes(pokemon.getTaxes());
            orderProduct.setQuantity(quantity);
        } else {
            orderProduct.setQuantity(orderProduct.getQuantity() + quantity);
        }
        orderProductRepository.save(orderProduct);

        Integer subtotalAmount = orderProductRepository.findByOrderId(order.getId()).stream()
                .mapToInt(op -> op.getSubtotal() * op.getQuantity())
                .sum();
        Integer taxesAmount = orderProductRepository.findByOrderId(order.getId()).stream()
                .mapToInt(op -> op.getTaxes() * op.getQuantity())
                .sum();
        Integer saveAmount = orderProductRepository.findByOrderId(order.getId()).stream()
                .mapToInt(op -> op.getSave() * op.getQuantity())
                .sum();
        Integer totalAmount = (subtotalAmount + taxesAmount) - saveAmount;
        order.setTotal(totalAmount);


        orderRepository.save(order);
    }

    private String setAddress(String address){
        String[] addresses = address.split(",\\s*");
        String addressFinal = "";
        String country = addresses[0];
        String state = addresses[1];
        String city = addresses[2];
        String zip = addresses[3];
        String addressDetail = addresses[4];
        addressFinal = String.format("%s, %s, %s, %s, %s", country, state, city, zip, addressDetail);
        return addressFinal;
    }

    private String setPaymentMethod(String paymentMethod){
        String paymentFinal = "";
        String[] payments = paymentMethod.split("\\s+");
        if (payments.length >= 2) {
            String cardNumber = payments[0];
            String cardType = payments[1];
            String lastFourDigits = cardNumber.substring(cardNumber.length() - 4);
            paymentFinal = String.format("XXXX-XXXX-XXXX-%s (%s)", lastFourDigits, cardType);
        }else if (payments.length == 1){
            paymentFinal = payments[0];
        }
        else {
            paymentFinal = paymentMethod;
        }
        return paymentFinal;
    }

    @Transactional(readOnly = true)
    public List<OrderProduct> getAllProductsInOrder(Long orderId) {
        return orderProductRepository.findByOrderId(orderId);
    }

    public List<OrderAndProductsDto> getAllOrdersByUser(Long userId) {
        List<Order> orders = orderRepository.findByUserId(userId);

        return orders.stream().map(order -> {
            OrderAndProductsDto dto = modelMapper.map(order, OrderAndProductsDto.class);
            List<OrderProduct> orderProducts = orderProductRepository.findByOrder(order);

            List<OrderProductDto> productDtos = orderProducts.stream()
                    .map(orderProduct -> {
                        OrderProductDto productDto = modelMapper.map(orderProduct, OrderProductDto.class);
                        return productDto;
                    })
                    .collect(Collectors.toList());

            dto.setProducts(productDtos);

            return dto;
        }).collect(Collectors.toList());
    }

}