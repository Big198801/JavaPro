package ru.big198801.mapper;


import org.springframework.stereotype.Component;
import ru.big198801.model.dto.PaymentDto;
import ru.big198801.model.dto.UsersDto;
import ru.big198801.model.entity.User;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class UsersMapper {
    private final PaymentMapper paymentMapper;

    public UsersMapper(PaymentMapper paymentMapper) {
        this.paymentMapper = paymentMapper;
    }

    public UsersDto toUsersDto(User users) {
        List<PaymentDto> paymentDtoList = null;
        if (!users.getPayments().isEmpty()) {
            paymentDtoList = paymentMapper.toDtoList(users.getPayments().stream().toList());
        }

        return new UsersDto(users.getId(), users.getUsername(), paymentDtoList);
    }

    public List<UsersDto> toUsersDtoList(List<User> usersList) {
        List<UsersDto> usersDtoList = new ArrayList<>();
        if (!usersList.isEmpty()) {
            for (User users : usersList) {
                usersDtoList.add(toUsersDto(users));
            }
        }

        return usersDtoList;
    }

    public User toEntity(UsersDto usersDto) {
        return new User()
                .setUsername(usersDto.username())
                .setCreatedAt(OffsetDateTime.now());
    }

}
