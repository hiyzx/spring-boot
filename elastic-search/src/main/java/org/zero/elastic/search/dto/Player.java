package org.zero.elastic.search.dto;

import lombok.Data;

/**
 * @author yezhaoxing
 * @date 2019/11/24
 */
@Data
public class Player {

    private String code;

    private String country;

    private String countryEn;

    private String displayAffiliation;

    private String displayName;

    private String displayNameEn;

    private String dob;

    private String draftYear;

    private String experience;

    private String firstInitial;

    private String firstName;

    private String firstNameEn;

    private String height;

    private String jerseyNo;

    private String lastName;

    private String lastNameEn;

    private String leagueId;

    private String playerId;

    private String position;

    private String schoolType;

    private String weight;

    private Team team;

}
