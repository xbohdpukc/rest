package com.pstech.rest.data;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class ProgressInfo {

    @SerializedName("line1")
    String statusLine1;

    @SerializedName("line2")
    String statusLine2;

    @SerializedName("line3")
    String statusLine3;

    /*
    public int getAcceptedBlocks() {
        return getIntValue("Accepted: ", line1);
    }

    public int getRejectedBlocks() {
        return getIntValue("Rejected: ", line2);
    }

    public int getHwErrors() {
        return getIntValue("HW Errors: ", line2);
    }

    private int getIntValue(String pattern, String line) {
        try {
            return Integer.valueOf(line.replace("Accepted: ", ""));
        } catch (Exception e) {
            logger.warning("Failed to get integer with pattern '" + pattern + "' from '" + line + "'");
            return 0;
        }
    }

*/
}
