package com.zzt.domain.constraint;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @auther 朱振霆~
 */
@Data
public class OnAndOffDutyStation {
    private List<String> startWorkStation;

    private List<String> endWorkStation;
    public OnAndOffDutyStation() {
        startWorkStation = new ArrayList<>();
        endWorkStation = new ArrayList<>();
    }

    public List<String> getStartWorkStation() {
        return startWorkStation;
    }

    public void setStartWorkStation(List<String> startWorkStation) {
        this.startWorkStation = startWorkStation;
    }

    public List<String> getEndWorkStation() {
        return endWorkStation;
    }

    public void setEndWorkStation(List<String> endWorkStation) {
        this.endWorkStation = endWorkStation;
    }
}
