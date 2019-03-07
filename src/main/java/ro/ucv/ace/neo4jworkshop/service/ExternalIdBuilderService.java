package ro.ucv.ace.neo4jworkshop.service;

import org.springframework.stereotype.Service;

import static java.lang.String.format;

@Service
public class ExternalIdBuilderService {

  public String buildHourExternalId(int year, int month, int day, int hour) {
    return format("%d%02d%02d%02d", year, month, day, hour);
  }
}
