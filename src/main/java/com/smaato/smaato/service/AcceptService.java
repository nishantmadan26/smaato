package com.smaato.smaato.service;

import java.util.Map;

public interface AcceptService {
    void accept(int id, String httpUrl);
    Map<String, Map<Integer, Integer>> getMap();
}
