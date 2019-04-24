package com.iahsnil.yasuo.manage.fallback;

import com.iahsnil.yasuo.manage.remote.NineOneRemote;
import org.springframework.stereotype.Component;

@Component
public class NineOneRemoteHystrix implements NineOneRemote {
    @Override
    public Object getList() {
        return "this message send failed";
    }

    @Override
    public Object refreshList() {
        return "refresh list failed";
    }

    @Override
    public Object excuteTask(int taskId) {
        return "excute " + taskId + "failed";
    }
}
