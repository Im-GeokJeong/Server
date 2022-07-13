package IGJ.imgeokjeong.util;

import IGJ.imgeokjeong.office.domain.Machine;
import IGJ.imgeokjeong.office.domain.Office;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.*;

public class DataUtil {
    private Map<String, List<String>> cropMap = new HashMap<>();
    public List<Office> getOffices() {
        File file = new File("/Users/one/Desktop/Im-GeokJeong/data/total_data.csv");
        List<Office> offices = new ArrayList<>();

        setCropMap();

        try {
            BufferedReader br = new BufferedReader(new FileReader(file, Charset.forName("EUC-KR")));
            String line;

            while ((line = br.readLine()) != null) {
                String[] tokens = line.split(",");

                String name = tokens[0];
                String phoneNumber = tokens[1];
                String streetNameAddress = tokens[2];
                String lotNumberAddress = tokens[3];
                String latitude = tokens[4];
                String longitude = tokens[5];

                List<Machine> machines = new ArrayList<>();
                if(Integer.parseInt(tokens[6]) != 0)
                    machines.add(new Machine("트랙터 및 작업기", Integer.parseInt(tokens[6]),
                            "https://igj.s3.ap-northeast-2.amazonaws.com/%E1%84%90%E1%85%B3%E1%84%85%E1%85%A2%E1%86%A8%E1%84%90%E1%85%A5+%E1%84%86%E1%85%B5%E1%86%BE+%E1%84%8C%E1%85%A1%E1%86%A8%E1%84%8B%E1%85%A5%E1%86%B8%E1%84%80%E1%85%B5.png"));
                if(Integer.parseInt(tokens[7]) != 0)
                    machines.add(new Machine("경운기 및 작업기", Integer.parseInt(tokens[7]),
                            "https://igj.s3.ap-northeast-2.amazonaws.com/%E1%84%80%E1%85%A7%E1%86%BC%E1%84%8B%E1%85%AE%E1%86%AB%E1%84%80%E1%85%B5+%E1%84%86%E1%85%B5%E1%86%BE+%E1%84%8C%E1%85%A1%E1%86%A8%E1%84%8B%E1%85%A5%E1%86%B8%E1%84%80%E1%85%B5.png"));
                if(Integer.parseInt(tokens[8]) != 0)
                    machines.add(new Machine("관리기 및 작업기", Integer.parseInt(tokens[8]),
                            "https://igj.s3.ap-northeast-2.amazonaws.com/%E1%84%80%E1%85%AA%E1%86%AB%E1%84%85%E1%85%B5%E1%84%80%E1%85%B5+%E1%84%86%E1%85%B5%E1%86%BE+%E1%84%8C%E1%85%A1%E1%86%A8%E1%84%8B%E1%85%A5%E1%86%B8%E1%84%80%E1%85%B5.png"));
                if(Integer.parseInt(tokens[9]) != 0)
                    machines.add(new Machine("땅속 작물 수확기", Integer.parseInt(tokens[9]),
                            "https://igj.s3.ap-northeast-2.amazonaws.com/%E1%84%84%E1%85%A1%E1%86%BC%E1%84%89%E1%85%A9%E1%86%A8+%E1%84%8C%E1%85%A1%E1%86%A8%E1%84%86%E1%85%AE%E1%86%AF+%E1%84%89%E1%85%AE%E1%84%92%E1%85%AA%E1%86%A8%E1%84%80%E1%85%B5.png"));
                if(Integer.parseInt(tokens[10]) != 0)
                    machines.add(new Machine("탈곡기 및 정선 작업기", Integer.parseInt(tokens[10]),
                            "https://igj.s3.ap-northeast-2.amazonaws.com/%E1%84%90%E1%85%A1%E1%86%AF%E1%84%80%E1%85%A9%E1%86%A8%E1%84%80%E1%85%B5+%E1%84%86%E1%85%B5%E1%86%BE+%E1%84%8C%E1%85%A5%E1%86%BC%E1%84%89%E1%85%A5%E1%86%AB+%E1%84%8C%E1%85%A1%E1%86%A8%E1%84%8B%E1%85%A5%E1%86%B8%E1%84%80%E1%85%B5.png"));
                if(Integer.parseInt(tokens[11]) != 0)
                    machines.add(new Machine("자주형 파종기", Integer.parseInt(tokens[11]),
                            "https://igj.s3.ap-northeast-2.amazonaws.com/%E1%84%8C%E1%85%A1%E1%84%8C%E1%85%AE%E1%84%92%E1%85%A7%E1%86%BC+%E1%84%91%E1%85%A1%E1%84%8C%E1%85%A9%E1%86%BC%E1%84%80%E1%85%B5.png"));
                if(Integer.parseInt(tokens[12]) != 0)
                    machines.add(new Machine("이앙 작업기", Integer.parseInt(tokens[12]),
                            "https://igj.s3.ap-northeast-2.amazonaws.com/%E1%84%8B%E1%85%B5%E1%84%8B%E1%85%A1%E1%86%BC+%E1%84%8C%E1%85%A1%E1%86%A8%E1%84%8B%E1%85%A5%E1%86%B8%E1%84%80%E1%85%B5.png"));
                if(Integer.parseInt(tokens[13]) != 0)
                    machines.add(new Machine("벼 수확 및 운반 작업기", Integer.parseInt(tokens[13]),
                            "https://igj.s3.ap-northeast-2.amazonaws.com/%E1%84%87%E1%85%A7+%E1%84%89%E1%85%AE%E1%84%92%E1%85%AA%E1%86%A8+%E1%84%86%E1%85%B5%E1%86%BE+%E1%84%8B%E1%85%AE%E1%86%AB%E1%84%87%E1%85%A1%E1%86%AB+%E1%84%8C%E1%85%A1%E1%86%A8%E1%84%8B%E1%85%A5%E1%86%B8%E1%84%80%E1%85%B5.png"));

                Office office = new Office(name, phoneNumber, streetNameAddress, lotNumberAddress, latitude, longitude);
                for (Machine machine : machines)
                    office.addMachine(machine);

                offices.add(office);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return offices;
    }

    private void setCropMap() {
        cropMap.put("트랙터 및 작업기", Arrays.asList("토양", "경작", "쟁기","쌀", "보리", "옥수수", "견과류"));
        cropMap.put("경운기 및 작업기", Arrays.asList("고구마", "감자", "마늘", "양파", "생강", "작약", "땅콩"));
        cropMap.put("관리기 및 작업기", Arrays.asList("제초", "구굴", "휴립", "비닐피복", "벼", "옥수수"));
        cropMap.put("땅속 작물 수확기", Arrays.asList("고구마", "감자", "마늘", "양파", "생강", "작약", "땅콩"));
        cropMap.put("탈곡기 및 정선 작업기", Arrays.asList("들깨", "참깨", "콩", "쌀", "보리"));
        cropMap.put("자주형 파종기", Arrays.asList("콩", "마늘", "씨앗"));
        cropMap.put("이앙 작업기", Arrays.asList("벼", "쌀"));
        cropMap.put("벼 수확 및 운반 작업기", Arrays.asList("벼", "쌀"));
    }

    public Map<String, List<String>> getCropMap() {
        return cropMap;
    }
}
