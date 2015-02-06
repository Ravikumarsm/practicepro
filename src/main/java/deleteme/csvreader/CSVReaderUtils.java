package deleteme.csvreader;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.lang.StringUtils;
import org.supercsv.io.CsvListReader;
import org.supercsv.io.ICsvListReader;
import org.supercsv.prefs.CsvPreference;

import java.io.FileReader;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by Admin on 1/2/2015.
 */
public class CSVReaderUtils {

    private static int lengthOfCsv = 0;

    public static void main(String... a) throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the CSV File ");
        String inputFileName = scanner.nextLine();
        System.out.println("Enter the Column to be compared ");
        int columnToBeCompared = scanner.nextInt();
        columnToBeCompared--;
        System.out.println("Enter the Column to be compared by");
        int columnToBeComparedBy = scanner.nextInt();
        columnToBeComparedBy--;
        compareEachCsvRowsOfGivenColumns(columnToBeCompared, columnToBeComparedBy, inputFileName);
    }

    public static void compareEachCsvRowsOfGivenColumns(int column, int anotherColumn, String inputFileName) throws Exception {
        List<String> columnToBeCompare = Lists.newArrayList();
        List<String> columnComparedBy  = Lists.newArrayList();
        List<String> dataToBeCompared = readVariableColumnsWithCsvListReader(inputFileName);
        if (column < lengthOfCsv && anotherColumn < lengthOfCsv){
            for (Object dataToBeSplit : dataToBeCompared){
                List<String> listToBeIterated =  Arrays.asList(StringUtils.split((String) dataToBeSplit, ","));
                prepareTheDataFromCSV(listToBeIterated,columnToBeCompare,columnComparedBy,column,anotherColumn);
            }
            Map<String, List<String>> unMatchedDataMap = compareTheGivenData(columnToBeCompare, columnComparedBy);
            for (Map.Entry<String, List<String>> unMatchedData : unMatchedDataMap.entrySet()){
                System.out.println("\nThe " + unMatchedData.getKey() + " does not match with ");
                for (String unMatchedRows : unMatchedData.getValue()){
                    System.out.println("\t\t"+unMatchedRows);
                }
            }
        }
        else {
            System.out.println("Please give the column numbers between 1 to "+ lengthOfCsv);
            main();
        }
    }

    private static List<String> readVariableColumnsWithCsvListReader(String fileName) throws Exception {
        ICsvListReader listReader = null;
        List<String> csvDataList = Lists.newArrayList();
        try {
            listReader = new CsvListReader(new FileReader(fileName), CsvPreference.STANDARD_PREFERENCE);
            listReader.getHeader(true);
            lengthOfCsv = listReader.length();
            while( (listReader.read()) != null ) {
                csvDataList.add(listReader.getUntokenizedRow());
            }
        }
        finally {
            if( listReader != null ) {
                listReader.close();
            }
        }
        return csvDataList;
    }

    private static Map<String, List<String>> compareTheGivenData(List<String> listOfDataToBeCompared, List<String> listOfDataComparedBy){
        Map<String,List<String>> unMatchedDataMap = Maps.newLinkedHashMap();
        for (String dataToBeCompared : listOfDataToBeCompared){
            List<String> listOfUnMatchedData = Lists.newArrayList();
            for (String dataToBeComparedBy : listOfDataComparedBy){
                if (!dataToBeCompared.equals(dataToBeComparedBy)){
                    listOfUnMatchedData.add(dataToBeComparedBy);
                }
            }
            unMatchedDataMap.put(dataToBeCompared,listOfUnMatchedData);
        }
        return unMatchedDataMap;
    }

    private static void prepareTheDataFromCSV(List<String> listToBeIterated, List<String> columnToBeCompare,List<String> columnComparedBy,int column,int anotherColumn){
        if (listToBeIterated.size()> column) {
            if (!columnToBeCompare.contains(listToBeIterated.get(column)))
                columnToBeCompare.add(listToBeIterated.get(column));
        }
        if (listToBeIterated.size()> anotherColumn) {
            if (!columnComparedBy.contains(listToBeIterated.get(anotherColumn)))
                columnComparedBy.add(listToBeIterated.get(anotherColumn));
        }
    }
}
