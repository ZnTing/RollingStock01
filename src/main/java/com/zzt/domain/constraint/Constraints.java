package com.zzt.domain.constraint;

import com.zzt.domain.constant.Constant;
import lombok.Data;

import java.util.Comparator;
import java.util.List;
import java.util.Map;

/**
 * @auther 朱振霆~
 */
@Data
public class Constraints {
    /**
     * 出退勤地点
     */
    private List<OnAndOffDutyStation> onAndOffDutyStations;
    /**
     * 出入段时间
     */
    private Map<String, Integer> inOutDepotTime;

    public Constraints() {
    }

    public Constraints(List<OnAndOffDutyStation> onAndOffDutyStations, Map<String, Integer> inOutDepotTime) {
        this.onAndOffDutyStations = onAndOffDutyStations;
        this.inOutDepotTime = inOutDepotTime;
    }

    public List<OnAndOffDutyStation> getOnAndOffDutyStations() {
        return onAndOffDutyStations;
    }

    public void setOnAndOffDutyStations(List<OnAndOffDutyStation> onAndOffDutyStations) {
        this.onAndOffDutyStations = onAndOffDutyStations;
        if (onAndOffDutyStations != null){
            this.onAndOffDutyStations.sort(Comparator.comparing(a -> a.getStartWorkStation().stream().noneMatch(b -> b.contains(Constant.CC))));
        }
    }

}
