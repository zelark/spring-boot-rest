package sun.rock.rest.clinet.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.zelark.dto.ProcessDTO;

import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class ProcessService {

  @Value("${resource.processes}")
  private String resource;

  @Value("${resource.processes}/{id}")
  private String resourceId;

  private RestTemplate restTemplate;

  @Autowired
  public ProcessService(RestTemplate restTemplate) {
    this.restTemplate = restTemplate;
  }

  public List<ProcessDTO> findAll() {
    return Arrays.stream(restTemplate.getForObject(resource, ProcessDTO[].class))
        .collect(toList());
  }

  public ProcessDTO find(Long id) {
    return restTemplate.getForObject(resourceId, ProcessDTO.class, id);
  }

  public ProcessDTO create(ProcessDTO dto) {
    return restTemplate.postForObject(resource, dto, ProcessDTO.class);
  }

  public ProcessDTO update(Long id, ProcessDTO dto) {
    return
        restTemplate.exchange(
            resourceId,
            HttpMethod.PUT,
            new HttpEntity<>(dto),
            ProcessDTO.class,
            id
        ).getBody();
  }

  public void delete(Long id) {
    restTemplate.delete(resourceId, id);
  }
}
