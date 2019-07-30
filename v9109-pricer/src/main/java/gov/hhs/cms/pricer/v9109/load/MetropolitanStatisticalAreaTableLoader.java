package gov.hhs.cms.pricer.v9109.load;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import gov.hhs.cms.pricer.v9109.model.MetropolitanStatisticalArea;

import java.io.IOException;
import java.util.List;

public class MetropolitanStatisticalAreaTableLoader {
    List<MetropolitanStatisticalArea> msaTable;

    public static List<MetropolitanStatisticalArea> getMetropolitanStatisticalAreaTable() throws IOException {
        ObjectMapper m = new ObjectMapper();
        List<MetropolitanStatisticalArea> msaTable =
                m.readValue(
                        MetropolitanStatisticalAreaTableLoader.class
                                .getClassLoader().getResourceAsStream("msaTable.json"),
                   new TypeReference<List<MetropolitanStatisticalArea>>(){});

        return msaTable;
    }
}
