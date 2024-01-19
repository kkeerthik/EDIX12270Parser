package org.edi;

import com.fasterxml.jackson.databind.JsonNode;

import com.fasterxml.jackson.databind.ObjectMapper;


import java.io.IOException;

public class JsonToEDI {

    public static void main(String[] args) {
        ISASegment isaSegment = new ISASegment();
        String jsonInput = "{" +
                "\"SegmentDelimiter\": \"~\"," +
                "\"DataElementDelimiter\": \"*\"," +
                "\"ISA\": {" +
                "    \"ISA01\": \"00\"," +
                "    \"ISA02\": \"          \"," +
                "    \"ISA03\": \"00\"," +
                "    \"ISA04\": \"          \"," +
                "    \"ISA05\": \"ZZ*SENDER_ID\"," +
                "    \"ISA06\": \"          \"," +
                "    \"ISA07\": \"ZZ*RECEIVER_ID\"," +
                "    \"ISA08\": \"77034\"," +
                "    \"ISA09\": \"YYMMDD\"," +
                "    \"ISA10\": \"HHMM\"," +
                "    \"ISA11\": \"^\"," +
                "    \"ISA12\": \"00501\"," +
                "    \"ISA13\": \"*000000001\"," +
                "    \"ISA14\": \"0\"," +
                "    \"ISA15\": \"T\"," +
                "    \"ISA16\": \":\"" +
                "}," +
                "    \"GS\": {" +
                "    \"GS01\": \"HS\"," +
                "    \"GS02\": \"AV0935\"," +
                "    \"GS03\": \"INSU123\"," +
                "    \"GS04\": \"20210101\"," +
                "    \"GS05\": \"1200\"," +
                "    \"GS06\": \"1\"," +
                "    \"GS07\": \"X\"," +
                "    \"GS08\": \"005010X279A1\"" +
                "}," +
                "    \"ST\": {" +
                "    \"ST01\": \"270\"," +
                "    \"ST02\": \"0001\"," +
                "    \"ST03\": \"005010X279A1\"" +
                "}," +
                "    \"BHT\": {" +
                "    \"BHT01\": \"0019\"," +
                "    \"BHT02\": \"00\"," +
                "    \"BHT03\": \"00123\"," +
                "    \"BHT04\": \"YYMMDD\"," +
                "    \"BHT05\": \"HHMM\"" +
                "} " +
                "}";


        try {
            jsonToEdiModel(jsonInput);
            //EDIModel ediModel = jsonToEdiModel(jsonInput);
            //if (ediModel != null) {
            //    String ediMessage = constructEDIMessage(ediModel);
            //    System.out.println(ediMessage);
            //}
        } catch (Exception e) {
            e.printStackTrace();
        }
    }it 


    private static void jsonToEdiModel(String json) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode parentNode = objectMapper.readTree(json);

            JsonNode ISANode = parentNode.get("ISA");
            ISASegment isaSegment = new ISASegment();
            isaSegment.setIsa01(ISANode.findValue("ISA01").toString());
            isaSegment.setIsa02(ISANode.findValue("ISA02").toString());
            isaSegment.setIsa03(ISANode.findValue("ISA03").toString());
            isaSegment.setIsa04(ISANode.findValue("ISA04").toString());
            isaSegment.setIsa05(ISANode.findValue("ISA05").toString());
            isaSegment.setIsa06(ISANode.findValue("ISA06").toString());
            isaSegment.setIsa07(ISANode.findValue("ISA07").toString());
            isaSegment.setIsa08(ISANode.findValue("ISA08").toString());
            isaSegment.setIsa09(ISANode.findValue("ISA09").toString());
            isaSegment.setIsa10(ISANode.findValue("ISA10").toString());
            isaSegment.setIsa11(ISANode.findValue("ISA11").toString());
            isaSegment.setIsa12(ISANode.findValue("ISA12").toString());
            isaSegment.setIsa13(ISANode.findValue("ISA13").toString());
            isaSegment.setIsa14(ISANode.findValue("ISA14").toString());
            isaSegment.setIsa15(ISANode.findValue("ISA15").toString());
            isaSegment.setIsa16(ISANode.findValue("ISA16").toString());

            String isaOutput = "ISA*" +
                    isaSegment.getIsa01() + "*" +
                    "          " + "*" +
                    isaSegment.getIsa03() + "*" +
                    "          " + "*" +
                    isaSegment.getIsa05() + "*" +
                    "          " + "*" +
                    isaSegment.getIsa06() + "*" +
                    "          " + "*" +
                    isaSegment.getIsa07() + "*" +
                    "          " + "*" +
                    isaSegment.getIsa08() + "*" +
                    isaSegment.getIsa09() + "*" +
                    isaSegment.getIsa10() + "*" +
                    isaSegment.getIsa11() + "*" +
                    isaSegment.getIsa12() + "*" +
                    isaSegment.getIsa13() + "*" +
                    isaSegment.getIsa14() + "*" +
                    isaSegment.getIsa15() + "*" +
                    isaSegment.getIsa16() + "~";

// Remove double quotes
            isaOutput = isaOutput.replaceAll("\"", "");

// Display the formatted ISA output
            System.out.println(isaOutput);

            JsonNode GSNode = parentNode.get("GS");
            GSSegment gsSegment = new GSSegment();
            gsSegment.setGs01(GSNode.findValue("GS01").toString());
            gsSegment.setGs02(GSNode.findValue("GS02").toString());
            gsSegment.setGs03(GSNode.findValue("GS03").toString());
            gsSegment.setGs04(GSNode.findValue("GS04").toString());
            gsSegment.setGs05(GSNode.findValue("GS05").toString());
            gsSegment.setGs06(GSNode.findValue("GS06").toString());
            gsSegment.setGs07(GSNode.findValue("GS07").toString());
            gsSegment.setGs08(GSNode.findValue("GS08").toString());

            // Generate the desired output using String concatenation
            String gsOutput = "GS*" +
                    gsSegment.getGs01() + "*" +
                    gsSegment.getGs02() + "*" +
                    gsSegment.getGs03() + "*" +
                    gsSegment.getGs04() + "*" +
                    gsSegment.getGs05() + "*" +
                    gsSegment.getGs06() + "*" +
                    gsSegment.getGs07() + "*" +
                    gsSegment.getGs08() + "~";

            // Remove double quotes
            gsOutput = gsOutput.replaceAll("\"", "");

            System.out.println(gsOutput);

            JsonNode STNode = parentNode.get("ST");
            STSegment stSegment = new STSegment();
            stSegment.setSt01(STNode.findValue("ST01").toString());
            stSegment.setSt02(STNode.findValue("ST02").toString());
            stSegment.setSt03(STNode.findValue("ST03").toString());

            // Generate the desired output without removing double quotes
            String stOutput = "ST*" +
                    stSegment.getSt01() + "*" +
                    stSegment.getSt02() + "*" +
                    stSegment.getSt03() + "~";

            // Remove double quotes
            stOutput = stOutput.replaceAll("\"", "");

            System.out.println(stOutput);

            JsonNode BHTNode = parentNode.get("BHT");
            BHTSegment bhtSegment = new BHTSegment();
            bhtSegment.setBht01(BHTNode.findValue("BHT01").toString());
            bhtSegment.setBht02(BHTNode.findValue("BHT02").toString());
            bhtSegment.setBht03(BHTNode.findValue("BHT03").toString());
            bhtSegment.setBht04(BHTNode.findValue("BHT04").toString());
            bhtSegment.setBht05(BHTNode.findValue("BHT05").toString());

            // Generate the desired output using String concatenation
            String bhtOutput = "BHT*" +
                    bhtSegment.getBht01() + "*" +
                    bhtSegment.getBht02() + "*" +
                    bhtSegment.getBht03() + "*" +
                    bhtSegment.getBht04() + "*" +
                    bhtSegment.getBht05() + "~";

               // Remove double quotes
               bhtOutput = bhtOutput.replaceAll("\"", "");

            System.out.println(bhtOutput);




            //return objectMapper.readValue(json, EDIModel.class);
        } catch (IOException e) {
            e.printStackTrace();
            //return null;
        }
    }

    private static String constructEDIMessage(EDIModel model) {
        String gsSegment = model.getGsSegment().toEDIString();
        String stSegment = model.getStSegment().toEDIString();
        String bhtSegment = model.getBhtSegment().toEDIString();// Replace with the actual method to retrieve the second segment.

        // Concatenate the two segments with a newline character if needed.
        return gsSegment + "\n" + stSegment + "\n" + bhtSegment;
    }
}

