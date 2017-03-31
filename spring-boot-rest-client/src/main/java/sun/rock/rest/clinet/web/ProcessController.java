package sun.rock.rest.clinet.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.HttpClientErrorException;
import sun.rock.server.dto.MessageDTO;
import sun.rock.server.dto.ProcessDTO;
import sun.rock.rest.clinet.service.ProcessService;

import java.io.IOException;

import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

@Controller
@RequestMapping("/")
public class ProcessController {

  @Autowired
  private ProcessService service;

  @Autowired
  private ObjectMapper mapper;

  @RequestMapping(method = POST)
  public String create(@ModelAttribute("newProcess") ProcessDTO dto) {
    service.create(dto);
    return "redirect:/";
  }

  @RequestMapping(method = GET)
  public String findAll(Model model) {
    model.addAttribute("processes", service.findAll());
    model.addAttribute("newProcess", new ProcessDTO());
    return "processes";
  }

  @RequestMapping(method = PUT)
  public String update(@RequestParam Long id, ProcessDTO dto) {
    service.update(id, dto);
    return "redirect:/";
  }

  @RequestMapping(method = DELETE)
  public String delete(@RequestParam Long id) {
    service.delete(id);
    return "redirect:/";
  }

  @ExceptionHandler(HttpClientErrorException.class)
  public String handleClientError(HttpClientErrorException ex, Model model) throws IOException {
    MessageDTO dto = mapper.readValue(ex.getResponseBodyAsByteArray(), MessageDTO.class);
    model.addAttribute("error", dto.getMessage());
    return findAll(model);
  }
}
