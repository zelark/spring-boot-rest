package ru.zelark.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.zelark.dto.MessageDTO;
import ru.zelark.dto.ProcessDTO;
import ru.zelark.service.ProcessNotFoundException;
import ru.zelark.service.ProcessService;

import javax.validation.Valid;
import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping("/api/processes")
public class ProcessController {

  @Autowired
  private ProcessService service;

  @Autowired
  private MessageSource messageSource;

  @RequestMapping(method = RequestMethod.GET)
  public List<ProcessDTO> findAll() {
    return service.findAll();
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
  public ProcessDTO findOne(@PathVariable Long id) {
    return service.findOne(id);
  }

  @RequestMapping(method = RequestMethod.POST)
  @ResponseStatus(HttpStatus.CREATED)
  public ProcessDTO create(@Valid @RequestBody ProcessDTO dto) {
    return service.create(dto);
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
  public ProcessDTO update(@PathVariable Long id, @RequestBody @Valid ProcessDTO dto) {
    return service.update(id, dto);
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void delete(@PathVariable Long id) {
    service.delete(id);
  }

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public MessageDTO handleValidationException(MethodArgumentNotValidException ex) {
    Locale locale = LocaleContextHolder.getLocale();
    String code = ex.getBindingResult().getFieldError().getDefaultMessage();
    return new MessageDTO(messageSource.getMessage(code, null, locale));
  }

  @ResponseStatus(HttpStatus.NOT_FOUND)
  @ExceptionHandler(ProcessNotFoundException.class)
  public MessageDTO handleNotFoundException(ProcessNotFoundException ex) {
    return new MessageDTO("Process not found");
  }
}
