package com.jbohorquez.emazon_hexagonal.infrastructure.output.jpa.adapter;

import com.jbohorquez.emazon_hexagonal.domain.model.User;
import com.jbohorquez.emazon_hexagonal.infrastructure.exception.AlreadyExistsException;
import com.jbohorquez.emazon_hexagonal.infrastructure.exception.NameTooLongException;
import com.jbohorquez.emazon_hexagonal.infrastructure.exception.NoDataFoundException;
import com.jbohorquez.emazon_hexagonal.infrastructure.output.jpa.entity.UserEntity;
import com.jbohorquez.emazon_hexagonal.infrastructure.output.jpa.mapper.UserEntityMapper;
import com.jbohorquez.emazon_hexagonal.infrastructure.output.jpa.repository.IUserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static com.jbohorquez.emazon_hexagonal.constants.ValidationConstants.NAME_MAX_LENGTH;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class UserJpaAdapterTest {

    @Mock
    private IUserRepository userRepository;

    @Mock
    private UserEntityMapper userEntityMapper;

    @InjectMocks
    private UserJpaAdapter userJpaAdapter;

    private User user;
    private UserEntity userEntity;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        user = new User();
        user.setName("John");

        userEntity = new UserEntity();
        userEntity.setName("John");
    }

    @Test
    void testSaveUser_Success() {
        when(userRepository.findByName(anyString())).thenReturn(Optional.empty());
        when(userEntityMapper.toEntity(any(User.class))).thenReturn(userEntity);

        userJpaAdapter.saveUser(user);

        verify(userRepository, times(1)).save(any(UserEntity.class));
    }

    @Test
    void testSaveUser_NameTooLong() {
        user.setName("A".repeat(NAME_MAX_LENGTH + 1));

        assertThrows(NameTooLongException.class, () -> userJpaAdapter.saveUser(user));

        verify(userRepository, never()).save(any(UserEntity.class));
    }

    @Test
    void testSaveUser_AlreadyExists() {
        when(userRepository.findByName(anyString())).thenReturn(Optional.of(userEntity));

        assertThrows(AlreadyExistsException.class, () -> userJpaAdapter.saveUser(user));

        verify(userRepository, never()).save(any(UserEntity.class));
    }

    @Test
    void testGetAllUser_Success() {
        when(userRepository.findAll()).thenReturn(List.of(userEntity));
        when(userEntityMapper.toUserList(anyList())).thenReturn(List.of(user));

        List<User> result = userJpaAdapter.getAllUser();

        assertFalse(result.isEmpty());
        verify(userRepository, times(1)).findAll();
    }

    @Test
    void testGetAllUser_NoDataFound() {
        when(userRepository.findAll()).thenReturn(Collections.emptyList());

        assertThrows(NoDataFoundException.class, () -> userJpaAdapter.getAllUser());

        verify(userRepository, times(1)).findAll();
    }

    @Test
    void testGetUserById_Success() {
        when(userRepository.findById(anyLong())).thenReturn(Optional.of(userEntity));
        when(userEntityMapper.toUser(any(UserEntity.class))).thenReturn(user);

        User result = userJpaAdapter.getUserById(1L);

        assertNotNull(result);
        verify(userRepository, times(1)).findById(anyLong());
    }

    @Test
    void testGetUserById_NoDataFound() {
        when(userRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(AlreadyExistsException.class, () -> userJpaAdapter.getUserById(1L));

        verify(userRepository, times(1)).findById(anyLong());
    }

    @Test
    void testUpdateUser() {
        when(userEntityMapper.toEntity(any(User.class))).thenReturn(userEntity);

        userJpaAdapter.updateUser(user);

        verify(userRepository, times(1)).save(any(UserEntity.class));
    }

    @Test
    void testDeleteUser() {
        userJpaAdapter.deleteUser(1L);

        verify(userRepository, times(1)).deleteById(anyLong());
    }

    @Test
    void testGetUsers_Success() {
        Page<UserEntity> userPage = mock(Page.class);
        when(userPage.isEmpty()).thenReturn(false);
        when(userRepository.findAll(any(PageRequest.class))).thenReturn(userPage);

        PageRequest pageRequest = PageRequest.of(0, 10);
        userJpaAdapter.getUsers(pageRequest);

        verify(userRepository, times(1)).findAll(any(PageRequest.class));
    }

    @Test
    void testGetUsers_NoDataFound() {
        Page<UserEntity> userPage = mock(Page.class);
        when(userPage.isEmpty()).thenReturn(true);
        when(userRepository.findAll(any(PageRequest.class))).thenReturn(userPage);

        PageRequest pageRequest = PageRequest.of(0, 10);

        assertThrows(NoDataFoundException.class, () -> userJpaAdapter.getUsers(pageRequest));

        verify(userRepository, times(1)).findAll(any(PageRequest.class));
    }

    @Test
    void testFindByEmail_Success() {
        when(userRepository.findByEmail(anyString())).thenReturn(Optional.of(userEntity));
        when(userEntityMapper.toUser(any(UserEntity.class))).thenReturn(user);

        Optional<User> result = userJpaAdapter.findByEmail("test@example.com");

        assertTrue(result.isPresent());
        verify(userRepository, times(1)).findByEmail(anyString());
    }

    @Test
    void testFindByEmail_NotFound() {
        when(userRepository.findByEmail(anyString())).thenReturn(Optional.empty());

        Optional<User> result = userJpaAdapter.findByEmail("test@example.com");

        assertFalse(result.isPresent());
        verify(userRepository, times(1)).findByEmail(anyString());
    }

    @Test
    void testFindAllUser_Success() {
        Page<UserEntity> userPage = mock(Page.class);
        when(userRepository.findAll(any(Pageable.class))).thenReturn(userPage);

        Pageable pageable = PageRequest.of(0, 10);
        userJpaAdapter.findAllUser(pageable);

        verify(userRepository, times(1)).findAll(any(Pageable.class));
    }
}