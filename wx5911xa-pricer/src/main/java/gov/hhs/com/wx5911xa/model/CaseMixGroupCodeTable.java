
package gov.hhs.com.wx5911xa.model;

import java.util.Map;

public class CaseMixGroupCodeTable {
  private Map<String, CaseMixGroup> cmgTable;

  public CaseMixGroupCodeTable(Map<String,CaseMixGroup> in) {
    this.cmgTable = in;
  }

  public CaseMixGroup getCmgEntry(String cmgCode) {
    return this.cmgTable.get(cmgCode);
  }
}
