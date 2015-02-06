package com.pdfstamper;

import java.util.List;
import java.util.Map;

/**
 * Created by Admin on 12/10/2014.
 */
public interface VCSIFCSCheckIn {

    /*
    *
    * added void method to the api as the previous method was misleading with the return type
    * @param enoviadata each element of the list will represent a single document
    *
    **/
    public boolean checkIn(List<Map> enoviadata);
}
