package IGJ.imgeokjeong.farm.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

@Slf4j
@RequiredArgsConstructor
@Service
public class FarmService {

    @Value("${farmMachineRentUrl}")
    private String farmMachineRentUrl;

    @Value("${farmMachineRentKey}")
    private String farmMachineRentKey;

    private final String[] attributes = {"officeNm", "officePhoneNumber", "rdnmadr", "lnmadr", "latitude", "longitude", "trctorHoldCo", "cultvtHoldCo", "manageHoldCo", "harvestHoldCo", "thresherHoldCo", "planterHoldCo", "transplantHoldCo", "rcepntHoldCo", "etcRentHoldCo", "phoneNumber", "institutionNm", "referenceDate", "instt_code", "instt_nm"};

    public JSONArray farmMachineRentAll() throws Exception {
        String urlBuilder = getUrl();

        String result =  callApi(urlBuilder);

        return parsing(result);
    }

    private String getUrl() throws Exception {
        return farmMachineRentUrl + "?" + URLEncoder.encode("serviceKey", "UTF-8") + "=" + farmMachineRentKey + /*Service Key*/
                "&" + URLEncoder.encode("pageNo", "UTF-8") + "=" + URLEncoder.encode("1", "UTF-8") + /*페이지 번호*/
                "&" + URLEncoder.encode("numOfRows", "UTF-8") + "=" + URLEncoder.encode("100", "UTF-8") + /*한 페이지 결과 수*/
                "&" + URLEncoder.encode("type", "UTF-8") + "=" + URLEncoder.encode("json", "UTF-8"); /*반환 형태*/
    }

    private String callApi(String urlBuilder) throws Exception {
        URL url = new URL(urlBuilder);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        System.out.println("Response code: " + conn.getResponseCode());
        BufferedReader rd;
        if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        rd.close();
        conn.disconnect();

        return sb.toString();
    }

    private JSONArray parsing(String result) throws Exception {
        JSONObject objData = (JSONObject)new JSONParser().parse(result);
        JSONObject response = (JSONObject) objData.get("response");
        JSONObject body = (JSONObject) response.get("body");
        ArrayList<JSONObject> items = (ArrayList<JSONObject>) body.get("items");

        JSONArray resultArray = new JSONArray();
        for (JSONObject item : items) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("사업소명", item.get("officeNm"));
            jsonObject.put("사업소전화번호", item.get("officePhoneNumber"));
            jsonObject.put("소재지도로명주소", item.get("rdnmadr"));
            jsonObject.put("소재지지번주소", item.get("lnmadr"));
            jsonObject.put("위도", item.get("latitude"));
            jsonObject.put("경도", item.get("longitude"));
            jsonObject.put("트랙터및작업기보유대수", item.get("trctorHoldCo"));
            jsonObject.put("경운기및작업기보유대수", item.get("cultvtHoldCo"));
            jsonObject.put("관리기및작업기보유대수", item.get("manageHoldCo"));
            jsonObject.put("땅속작물수확기보유대수", item.get("harvestHoldCo"));
            jsonObject.put("탈곡기및정선작업기보유대수", item.get("thresherHoldCo"));
            jsonObject.put("자주형파종기보유대수", item.get("planterHoldCo"));
            jsonObject.put("이앙작업기보유대수", item.get("transplantHoldCo"));
            jsonObject.put("벼수확및운반작업기보유대수", item.get("rcepntHoldCo"));
            jsonObject.put("기타임대농기계보유정보", item.get("etcRentHoldCo"));
            jsonObject.put("관리기관전화번호", item.get("phoneNumber"));
            jsonObject.put("관리기관명", item.get("institutionNm"));
            jsonObject.put("데이터기준일자", item.get("referenceDate"));
            jsonObject.put("제공기관코드", item.get("instt_code"));
            jsonObject.put("제공기관명", item.get("instt_nm"));
            resultArray.add(jsonObject);
        }

        System.out.println(resultArray);

        return resultArray;
    }

    // private init & common methods

}