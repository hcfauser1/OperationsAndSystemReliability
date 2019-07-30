package gov.hhs.cms.pricer.v9109.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class WageIndexMap {
    private HashMap<String, List<CoreBasedStatisticalArea>> cbsaMap = new HashMap<>();
    private HashMap<String, List<MetropolitanStatisticalArea>> msaMap = new HashMap<>();

    private WageIndexMap() {
    }

    public static WageIndexMap WageIndexMapFactory(List<CoreBasedStatisticalArea> cbsaTable,
                                                   List<MetropolitanStatisticalArea> msaTable) {

        return buildWageIndexMap(cbsaTable,msaTable);
    }


    private static WageIndexMap buildWageIndexMap(List<CoreBasedStatisticalArea> cbsaTable, List<MetropolitanStatisticalArea> msaTable) {

        WageIndexMap wim = new WageIndexMap();

        for (CoreBasedStatisticalArea entry : cbsaTable) {
            List<CoreBasedStatisticalArea> list = wim.cbsaMap.get(entry.getCbsaNumber());
            if (list == null) {
                list = new ArrayList<>();
            }
            list.add(entry);
            wim.cbsaMap.put(entry.getCbsaNumber(), list);
        }
        for (List<CoreBasedStatisticalArea> entry : wim.cbsaMap.values()) {
            Collections.sort(entry);
            Collections.reverse(entry);
        }

        for (MetropolitanStatisticalArea entry : msaTable) {
            List<MetropolitanStatisticalArea> list = wim.msaMap.get(entry.getMsaNumber());
            if (list == null) {
                list = new ArrayList<>();
            }
            list.add(entry);
            wim.msaMap.put(entry.getMsaNumber(), list);
        }
        for (List<MetropolitanStatisticalArea> entry : wim.msaMap.values()) {
            Collections.sort(entry);
            Collections.reverse(entry);
        }

        return wim;
    }

    public CoreBasedStatisticalArea getCbsaWageIndex(String cbsa, String effectiveDate) {
        List<CoreBasedStatisticalArea> entries = this.cbsaMap.get(cbsa);
    
        if (entries == null) {
          return null;
        }
    
        for (CoreBasedStatisticalArea e : entries) {
          if (e.getEffectiveDate().compareTo(effectiveDate) <= 0) {
            return e;
          }
        }
        return null;
    }
    
    public MetropolitanStatisticalArea getMsaWageIndex(String msa, String effectiveDate) {
        List<MetropolitanStatisticalArea> entries = this.msaMap.get(msa);
    
        if (entries == null) {
          return null;
        }
    
        for (MetropolitanStatisticalArea e : entries) {
          if (e.getEffectiveDate().compareTo(effectiveDate) <= 0) {
            return e;
          }
        }
        return null;
    }
}
