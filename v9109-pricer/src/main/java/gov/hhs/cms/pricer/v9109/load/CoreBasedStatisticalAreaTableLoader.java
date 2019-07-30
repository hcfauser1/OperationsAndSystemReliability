package gov.hhs.cms.pricer.v9109.load;

import gov.hhs.cms.pricer.v9109.model.CoreBasedStatisticalArea;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class CoreBasedStatisticalAreaTableLoader {
    public static List<CoreBasedStatisticalArea> getCoreBasedStatisticalAreaTable() throws IOException {
        //ObjectMapper m = new ObjectMapper();
        ArrayList<CoreBasedStatisticalArea> cbsaTable = new ArrayList<>();
//        m.readValue(
//                CoreBasedStatisticalAreaTableLoader.class
//                        .getClassLoader().getResourceAsStream("cbsa-2019.csv"),
//                new TypeReference<List<CoreBasedStatisticalArea>>(){});

        InputStreamReader csvIn = new InputStreamReader(
                CoreBasedStatisticalAreaTableLoader
                .class.getClassLoader()
                .getResourceAsStream("cbsa-2019.csv"));

        for (CSVRecord record: CSVFormat.DEFAULT.parse(csvIn)){
            CoreBasedStatisticalArea entry =
                    new CoreBasedStatisticalArea(
                            record.get(0).trim(),
                            record.get(1).trim(),
                            new BigDecimal(record.get(2))
                    );
            cbsaTable.add(entry);
        }
        return cbsaTable;
    }
}
