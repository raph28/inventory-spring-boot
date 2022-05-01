package com.ennea.inventory.Util;

import com.ennea.inventory.entity.CSVRowObject;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class InventoryCSVParser implements FileParser {

    private static final Logger logger= LoggerFactory.getLogger(InventoryCSVParser.class);

    public List<CSVRowObject> parse(InputStream inputStream){
        try{
            DateFormat sourceFormat = new SimpleDateFormat("dd/MM/yyyy");
            CSVReader reader=
                    new CSVReaderBuilder(new InputStreamReader(inputStream)).
                            withSkipLines(1).
                            build();
            List<CSVRowObject> csvObjectList=reader.readAll().stream().map(data-> {
                try{
                    CSVRowObject csvObject= new CSVRowObject();
                    csvObject.setCode(data[0]);
                    csvObject.setName(data[1]);
                    csvObject.setBatch(data[2]);
                    csvObject.setStock(Integer.parseInt(data[3]));
                    csvObject.setDeal(Integer.parseInt(data[4]));
                    csvObject.setFree(Integer.parseInt(data[5]));
                    csvObject.setMrp(new BigDecimal(data[6]));
                    csvObject.setRate(new BigDecimal(data[7]));
                    csvObject.setExp(sourceFormat.parse(data[8]));
                    csvObject.setCompany(data[9]);
                    csvObject.setSupplier(data[10]);
                    return csvObject;
                }
                catch(java.text.ParseException e){
                    logger.error("Unable to parse row due to error {} for data {}",e, Arrays.deepToString(data));
                    System.out.println("unable to validate data");
                    return null;
                }
                catch(Exception e){
                    logger.error("Unable to parse row due to error {} for data {}",e, Arrays.deepToString(data));
                    return null;
                }
            }).filter(Objects::nonNull).collect(Collectors.toList());
            logger.info("extracted {} record out of csvfile", csvObjectList.size() );

            return csvObjectList;
        } catch (IOException e) {
            logger.error("Unable to parse file due to error {}",e);
            e.printStackTrace();
            return new ArrayList<>();
        }

    }
}
