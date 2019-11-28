package org.zero.elastic.search.dto;

import lombok.Data;

/**
 * @author yezhaoxing
 * @date 2019/11/24
 */
@Data
public class Team {

    private String abbr;

    private String city;

    private String cityEn;

    private String code;

    private String conference;

    private String displayAbbr;

    private String displayConference;

    private String division;

    private String id;

    private boolean isAllStarTeam;

    private boolean isLeagueTeam;

    private String leagueId;

    private String name;

    private String nameEn;
}
