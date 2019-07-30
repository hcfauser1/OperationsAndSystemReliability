
package gov.hhs.cms.pricer.v9109.model;

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
