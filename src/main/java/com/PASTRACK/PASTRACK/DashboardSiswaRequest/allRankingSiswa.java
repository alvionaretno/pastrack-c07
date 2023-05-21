package com.PASTRACK.PASTRACK.DashboardSiswaRequest;


import lombok.Data;

@Data
public class allRankingSiswa {

    int rankingAngkatanAllSemester;
    int rankingKelasCurrentSemester;
    int rankingAngkatanCurrentSemester;


    public allRankingSiswa (int rankingAngkatanAllSemester, int rankingKelasCurrentSemester, int rankingAngkatanCurrentSemester) {
        this.rankingAngkatanAllSemester = rankingAngkatanAllSemester;
        this.rankingKelasCurrentSemester = rankingKelasCurrentSemester;
        this.rankingAngkatanCurrentSemester = rankingAngkatanCurrentSemester;
    }

}