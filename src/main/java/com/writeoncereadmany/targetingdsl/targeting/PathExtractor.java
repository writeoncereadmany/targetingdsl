package com.writeoncereadmany.targetingdsl.targeting;

import java.util.List;
import java.util.Map;

public interface PathExtractor {

    static Object extractPath(List<String> path, Map impression) {
        Object iterator = impression;
        for(String element : path) {
            if(iterator instanceof Map) {
                iterator = ((Map) iterator).get(element);
            } else {
                return null;
            }
        }
        return iterator;
    }
}
