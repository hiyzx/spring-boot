package org.zero.elastic.search.repository;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.zero.elastic.search.dto.EsEntity;
import org.zero.elastic.search.dto.NbaResponse;
import org.zero.elastic.search.dto.Player;
import org.zero.elastic.search.dto.Team;
import org.zero.elastic.search.util.EsUtil;
import org.zero.elastic.search.util.PageResultVO;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author yezhaoxing
 * @date 2019/11/24
 */
@Service
public class PlayerService {

    private static final String INDEX = "player";

    @Resource
    private EsUtil esUtil;

    public void add() {
        String response = HttpUtil.get("https://china.nba.com/static/data/league/playerlist.json");
        JSONObject jsonObject = JSONUtil.parseObj(response);
        JSONArray jsonArray = jsonObject.getJSONObject("payload").getJSONArray("players");
        ArrayList<NbaResponse> nbaResponses = jsonArray.toList(NbaResponse.class);
        List<EsEntity> players = new ArrayList<>();
        List<EsEntity> teams = new ArrayList<>();
        Snowflake snowflake = new Snowflake(1, 1);
        for (NbaResponse nbaResponse : nbaResponses) {
            Player player = nbaResponse.getPlayerProfile();
            Team team = nbaResponse.getTeamProfile();
            String id = String.valueOf(snowflake.nextId());
            player.setTeam(team);
            players.add(new EsEntity(id, JSONUtil.toJsonStr(player)));
        }
        esUtil.insertBatch("player", players);
    }

    public PageResultVO<Player> findByName(String name, Integer from, Integer size){
        BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder();
        if (StringUtils.hasText(name)) {
            boolQueryBuilder.must(QueryBuilders.matchQuery("displayName", name));
        }
        SearchSourceBuilder builder = new SearchSourceBuilder();
        builder.from(from).size(size).query(boolQueryBuilder);
        return esUtil.search(INDEX, builder, Player.class);
    }
}
