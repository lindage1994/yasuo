package com.iahsnil.yasuo.manage.fallback;

import com.iahsnil.yasuo.manage.remote.NineOneRemote;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class NineOneRemoteHystrix implements NineOneRemote {
    @Override
    public List<Object> getList() {
        return null;
    }

    @Override
    public Object refreshList(int page) {
        return "refresh list failed";
    }

    @Override
    public Object excuteTask(int taskId) {
        return "excute " + taskId + "failed";
    }
}
