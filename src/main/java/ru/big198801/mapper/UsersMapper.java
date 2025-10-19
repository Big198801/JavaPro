package ru.big198801.mapper;


import org.springframework.stereotype.Component;
import ru.big198801.model.dto.UsersDto;
import ru.big198801.model.entity.Users;
import java.util.ArrayList;
import java.util.List;

@Component
public class UsersMapper {
    private final ProductsMapper productsMapper;

    public UsersMapper(ProductsMapper productsMapper) {
        this.productsMapper = productsMapper;
    }

    public UsersDto toUsersDto(Users users) {
        return new UsersDto(users.getId(), users.getUsername(), productsMapper.toProductsDtoList(users.getProducts().stream().toList()));
    }

    public List<UsersDto> toUsersDtoList(List<Users> usersList) {
        List<UsersDto> usersDtoList = new ArrayList<>();
        for (Users users : usersList) {
            usersDtoList.add(toUsersDto(users));
        }
        return usersDtoList;
    }
}
