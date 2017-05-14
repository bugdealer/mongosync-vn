
package com.example.dpinventory;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

import org.springframework.data.mongodb.core.mapping.Document;






public class DpInventory {

   
    private Integer dpId;
   
    private Integer tableSize;
   
    private Integer blockSize;
    
    private Integer reservedSize;
  
    private List<BlockFlag> blockFlag = new ArrayList<BlockFlag>();

    /**
     * 
     * @return
     *     The dpId
     */
  
    public Integer getDpId() {
        return dpId;
    }

    /**
     * 
     * @param dpId
     *     The dpId
     */
  
    public void setDpId(Integer dpId) {
        this.dpId = dpId;
    }

    /**
     * 
     * @return
     *     The tableSize
     */
  
    public Integer getTableSize() {
        return tableSize;
    }

    /**
     * 
     * @param tableSize
     *     The tableSize
     */
  
    public void setTableSize(Integer tableSize) {
        this.tableSize = tableSize;
    }

    /**
     * 
     * @return
     *     The blockSize
     */
   
    public Integer getBlockSize() {
        return blockSize;
    }

    /**
     * 
     * @param blockSize
     *     The blockSize
     */
    
    public void setBlockSize(Integer blockSize) {
        this.blockSize = blockSize;
    }

    /**
     * 
     * @return
     *     The reservedSize
     */
   
    public Integer getReservedSize() {
        return reservedSize;
    }

    /**
     * 
     * @param reservedSize
     *     The reservedSize
     */
   
    public void setReservedSize(Integer reservedSize) {
        this.reservedSize = reservedSize;
    }

    /**
     * 
     * @return
     *     The blockFlag
     */
  
    public List<BlockFlag> getBlockFlag() {
        return blockFlag;
    }

    /**
     * 
     * @param blockFlag
     *     The blockFlag
     */
   
    public void setBlockFlag(List<BlockFlag> blockFlag) {
        this.blockFlag = blockFlag;
    }

	@Override
	public String toString() {
		return "DpInventory [dpId=" + dpId + ", tableSize=" + tableSize + ", blockSize=" + blockSize + ", reservedSize="
				+ reservedSize + ", blockFlag=" + blockFlag + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((blockFlag == null) ? 0 : blockFlag.hashCode());
		result = prime * result + ((blockSize == null) ? 0 : blockSize.hashCode());
		result = prime * result + ((dpId == null) ? 0 : dpId.hashCode());
		result = prime * result + ((reservedSize == null) ? 0 : reservedSize.hashCode());
		result = prime * result + ((tableSize == null) ? 0 : tableSize.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DpInventory other = (DpInventory) obj;
		if (blockFlag == null) {
			if (other.blockFlag != null)
				return false;
		} else if (!blockFlag.equals(other.blockFlag))
			return false;
		if (blockSize == null) {
			if (other.blockSize != null)
				return false;
		} else if (!blockSize.equals(other.blockSize))
			return false;
		if (dpId == null) {
			if (other.dpId != null)
				return false;
		} else if (!dpId.equals(other.dpId))
			return false;
		if (reservedSize == null) {
			if (other.reservedSize != null)
				return false;
		} else if (!reservedSize.equals(other.reservedSize))
			return false;
		if (tableSize == null) {
			if (other.tableSize != null)
				return false;
		} else if (!tableSize.equals(other.tableSize))
			return false;
		return true;
	}

}
