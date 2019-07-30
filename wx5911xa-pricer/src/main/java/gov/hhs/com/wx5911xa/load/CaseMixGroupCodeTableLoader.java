
package gov.hhs.com.wx5911xa.load;

import gov.hhs.com.wx5911xa.model.CaseMixGroup;
import gov.hhs.com.wx5911xa.model.CaseMixGroupCodeTable;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class CaseMixGroupCodeTableLoader {

    public static CaseMixGroupCodeTable getCaseMixGroupCodeTable(String year)
    throws IOException {


        InputStreamReader csvIn = new InputStreamReader(
            CaseMixGroupCodeTableLoader.class
                    .getClassLoader()
                    .getResourceAsStream("cmg-codes-" + year + ".csv"));

        Map<String, CaseMixGroup> cmgTable = new HashMap<>();

        int rowNum = 0;
        for (CSVRecord record : CSVFormat.DEFAULT.parse(csvIn)) {
            if (rowNum == 0) {
                rowNum += 1;
                continue;
            }

            String cmgCode = record.get(0).trim();
            CaseMixGroup entry = new CaseMixGroup(cmgCode,
                    CaseMixGroupCodeTableLoader.parseDoubleWithError(record.get(1).trim(), rowNum, 2),
                    CaseMixGroupCodeTableLoader.parseDoubleWithError(record.get(2).trim(), rowNum, 3),
                    CaseMixGroupCodeTableLoader.parseDoubleWithError(record.get(3).trim(), rowNum, 4),
                    CaseMixGroupCodeTableLoader.parseDoubleWithError(record.get(4).trim(), rowNum, 5),
                    CaseMixGroupCodeTableLoader.parseIntWithError(record.get(5).trim(), rowNum, 6),
                    CaseMixGroupCodeTableLoader.parseIntWithError(record.get(6).trim(), rowNum, 7),
                    CaseMixGroupCodeTableLoader.parseIntWithError(record.get(7).trim(), rowNum, 8),
                    CaseMixGroupCodeTableLoader.parseIntWithError(record.get(8).trim(), rowNum, 9));
            cmgTable.put(cmgCode, entry);
            rowNum += 1;
        }
        return new CaseMixGroupCodeTable(cmgTable);
    }

    private static int parseIntWithError(String s, int r, int f) {
        try {
            return Integer.parseInt(s);
        } catch (NumberFormatException e) {
            throw e;
        }
    }

    private static double parseDoubleWithError(String s, int r, int f) {
        try {
            return Double.parseDouble(s);
        } catch (NumberFormatException e) {
            throw e;
        }
    }

}
