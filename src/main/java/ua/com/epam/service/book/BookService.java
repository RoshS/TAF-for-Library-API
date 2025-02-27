package ua.com.epam.service.book;

import lombok.AllArgsConstructor;
import ua.com.epam.core.client.rest.RestClient;
import ua.com.epam.entity.book.Book;
import ua.com.epam.service.config.base.*;
import ua.com.epam.service.book.config.custom.GetAuthorRelatedDataArr;
import ua.com.epam.service.config.common.GetObjWithParams;
import ua.com.epam.service.config.common.SearchForObjArr;

import java.util.List;
import java.util.stream.Collectors;

import static ua.com.epam.config.URI.*;

@AllArgsConstructor
public class BookService {
    private RestClient client;

    public GetObj getBook(long bookId) {
        return new GetObj(client, String.format(GET_BOOK_SINGLE_OBJ, bookId));
    }

    public GetObjWithParams getAllBooks() {
        return new GetObjWithParams(client, GET_ALL_BOOKS_ARR);
    }

    public SearchForObjArr searchForBooks() {
        return new SearchForObjArr(client, SEARCH_FOR_EXISTED_GENRES_ARR);
    }

    public GetObjWithParams getAllBooksInGenre(long genreId) {
        return new GetObjWithParams(client, String.format(GET_ALL_BOOKS_IN_GENRE, genreId));
    }

    public GetAuthorRelatedDataArr getAllBooksOfAuthor(long authorId) {
        return new GetAuthorRelatedDataArr(client, String.format(GET_ALL_BOOKS_OF_AUTHOR, authorId));
    }

    public GetObj getAllBooksOfAuthorInGenre(long authorId, long genreId) {
        return new GetObj(client, String.format(GET_ALL_BOOKS_IN_GENRE_OF_AUTHOR, authorId, genreId));
    }

    public PostObj postBook(Book book, long authorId, long genreId) {
        return new PostObj(client, String.format(POST_BOOK_SINGLE_OBJ, authorId, genreId), book);
    }

    public PostArr postBooks(List<Book> booksToPost, long authorId, long genreId) {
        List<Object> objs = booksToPost.stream()
                .map(b -> (Object) b)
                .collect(Collectors.toList());
        return new PostArr(client, String.format(POST_BOOK_SINGLE_OBJ, authorId, genreId), objs);
    }

    public PutObj updateBook(Book updated) {
        return new PutObj(client, PUT_BOOK_SINGLE_OBJ, updated);
    }

    public DeleteObj deleteBook(long bookId) {
        return new DeleteObj(client, String.format(DELETE_BOOK_SINGLE_OBJ, bookId));
    }
}