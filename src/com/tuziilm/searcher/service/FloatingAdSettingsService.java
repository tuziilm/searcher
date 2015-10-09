package com.tuziilm.searcher.service;

import com.tuziilm.searcher.domain.FloatingAdSettings;
import com.tuziilm.searcher.persistence.FloatingAdSettingsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class FloatingAdSettingsService extends ListBasedCacheSupportService<FloatingAdSettings> {
    @Autowired
    public void setFloatingAdSettingsMapper(FloatingAdSettingsMapper floatingAdSettingsMapper) {
        this.mapper = floatingAdSettingsMapper;
    }

    public FloatingAdSettings getfloatingAdSettingsCache(){
        Collection<FloatingAdSettings> settings= getCache();
        if(settings==null || settings.isEmpty()){
            return null;
        }
        return ((List<FloatingAdSettings>)settings).get(0);
    }
}
