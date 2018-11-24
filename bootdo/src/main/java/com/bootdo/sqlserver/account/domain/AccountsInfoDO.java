package com.bootdo.sqlserver.account.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * model for AccountsInfo
 * Created by 13477 on 2017/6/20.
 */
public class AccountsInfoDO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer userId;
    private Integer gameId;
    private String accounts;
    private String nickName;
    private Integer customID;
    private  Integer loveLiness;
    private Integer agentLevel;     //代理级别，这里表示为是否为代理的标识
    private Integer playingGame;    //上级代理gameId
   // @JsonFormat(locale = SysConstant.JsonFormat.DEFAULT_LOCAL, timezone = SysConstant.JsonFormat.DEFAULT_TIMEZONE, pattern = SysConstant.JsonFormat.DEFAULT_PATTERN)
    private Timestamp registerDate;
    private Timestamp superAgentTime;

    public Integer getCustomID() {
        return customID;
    }

    private Long insureScore;

    private Integer _playingGame;

    public Integer getLoveLiness() {
        return loveLiness;
    }


    public Integer getUserId() {
        return userId;
    }

    public Integer getGameId() {
        return gameId;
    }

    public String getAccounts() {
        return accounts;
    }

    public String getNickName() {
        return nickName;
    }

    public Integer getAgentLevel() {
        return agentLevel;
    }

    public Integer getPlayingGame() {
        return playingGame;
    }

    public Timestamp getRegisterDate() {
        return registerDate;
    }

    public Timestamp getSuperAgentTime() {
        return superAgentTime;
    }

    public Long getInsureScore() {
        return insureScore;
    }



    public static class Builder {
        private Integer gameId;
        private Integer agentLevel;
        private Integer playingGame;
        private Integer _playingGame;

        public Builder gameId(Integer val) {
            this.gameId = val;
            return this;
        }

        public Builder agentLevel(Integer val) {
            this.agentLevel = val;
            return this;
        }

        public Builder playingGame(Integer val) {
            this.playingGame = val;
            return this;
        }

        public Builder _playingGame(Integer val) {
            this._playingGame = val;
            return this;
        }

    }
}
