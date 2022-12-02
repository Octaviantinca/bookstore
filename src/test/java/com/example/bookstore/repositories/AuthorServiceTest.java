package com.example.bookstore.repositories;

import com.example.bookstore.dto.AuthorDTO;
import com.example.bookstore.models.Author;
import com.example.bookstore.models.Book;
import com.example.bookstore.services.AuthorService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Transactional
class AuthorServiceTest {

    @Autowired
    private AuthorService underTest;


    @Test
    void bringAllAuthors() {
        //given

        //when
        List<AuthorDTO> expected = underTest.getAuthors();

        //then
        assertThat(expected.size()).isEqualTo(2);
    }
}