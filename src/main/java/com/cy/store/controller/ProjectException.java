package com.cy.store.controller;
import com.cy.store.controller.ex.*;
import com.cy.store.service.ex.*;
import com.cy.store.util.JsonResult;
import com.cy.store.util.State;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ProjectException {

    @ExceptionHandler(UsernameDuplicatedException.class)
    public JsonResult<Void> doUsernameDuplicatedException(UsernameDuplicatedException e) {
        return new JsonResult<>(State.USERNAME_ERROR,e.getMessage(),null);
    }

    @ExceptionHandler(InsertException.class)
    public JsonResult<Void> doInsertException(InsertException e) {
        return new JsonResult<>(State.INSERT_ERROR,e.getMessage(),null);
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public JsonResult<Void> doUsernameNotFoundException(UsernameNotFoundException e){
        return new JsonResult<>(State.USERNAME_ERROR,e.getMessage(),null);
    }

    @ExceptionHandler(PasswordNotMatchException.class)
    public JsonResult<Void> doPasswordNotMatchException(PasswordNotMatchException e){
        return new JsonResult<>(State.PASSWORD_ERROR,e.getMessage(),null);
    }

    @ExceptionHandler(UpdateException.class)
    public JsonResult<Void> doUpdateException(UpdateException e){
        return new JsonResult<>(State.UPDATE_ERROR,e.getMessage(),null);
    }

    @ExceptionHandler(FileEmptyException.class)
    public JsonResult<Void> doFileEmptyException(FileEmptyException e){
        return new JsonResult<>(State.FILE_ERROR,e.getMessage(),null);
    }

    @ExceptionHandler(FileSizeException.class)
    public JsonResult<Void> doFileSizeException(FileSizeException e){
        return new JsonResult<>(State.FILE_ERROR,e.getMessage(),null);
    }

    @ExceptionHandler(FileTypeException.class)
    public JsonResult<Void> doFileTypeException(FileTypeException e){
        return new JsonResult<>(State.FILE_ERROR,e.getMessage(),null);
    }

    @ExceptionHandler(FileUploadIOException.class)
    public JsonResult<Void> doFileUploadIOException(FileUploadIOException e){
        return new JsonResult<>(State.FILE_ERROR,e.getMessage(),null);
    }

    @ExceptionHandler(FileStateException.class)
    public JsonResult<Void> doFileStateException(FileStateException e){
        return new JsonResult<>(State.FILE_ERROR,e.getMessage(),null);
    }

    @ExceptionHandler(AddressCountLimitException.class)
    public JsonResult<Void> doAddressException(AddressCountLimitException e){
        return new JsonResult<>(State.ADDRESS_ERROR,e.getMessage(),null);
    }

    /*@ExceptionHandler(Exception.class)
    public JsonResult<Void> doException(Exception e) {
        return new JsonResult<>(State.UNKNOWN_ERROR,"系统繁忙，请稍后再试！",null);
    }*/

    @ExceptionHandler(AddressNotFoundException.class)
    public JsonResult<Void> doAddressNotFoundException(AddressNotFoundException e){
        return new JsonResult<>(State.ADDRESS_ERROR,e.getMessage(),null);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public JsonResult<Void> doAccessDeniedException(AccessDeniedException e){
        return new JsonResult<>(State.ACCESS_ERROR,e.getMessage(),null);
    }

    @ExceptionHandler(DeleteException.class)
    public JsonResult<Void> doDeleteException(DeleteException e){
        return new JsonResult<>(State.DELETE_ERROR,e.getMessage(),null);
    }

    @ExceptionHandler(CartNotFoundException.class)
    public JsonResult<Void> doCartNotFoundException(CartNotFoundException e){
        return new JsonResult<>(State.CARTNOTFOUND_ERROR,e.getMessage(),null);
    }



    /*@ExceptionHandler(ServiceException.class)
    public JsonResult<Void> handleException(Throwable e){

        JsonResult<Void> result = new JsonResult<>();
        if (e instanceof UsernameDuplicatedException) {
            result.setState(State.USERNAME_ERROR);
            result.setMessage("用户名已被占用！");
        } else if (e instanceof InsertException) {
            result.setState(State.INSERT_ERROR);
            result.setMessage("注册时发生错误！");
        }
        return result;
    }*/

}
