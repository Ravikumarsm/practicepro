package com.pdfstamper;

import java.util.List;
import java.util.Map;

/**
 * Created by Admin on 12/2/2014.
 */
public class VCSIFCSCheckOutImpl implements VCSIFCSCheckOut {
    public VCSIFCSCheckOutImpl() {
    }

    public String checkOut(Map enoviadata) {
        return "D:\\temp\\checkOutFolder\\"+enoviadata.get("filename");
    }

    public String checkOut(List<Map> enoviadata) {
        return "D:\\temp\\checkOutFolder\\";
    }
}
