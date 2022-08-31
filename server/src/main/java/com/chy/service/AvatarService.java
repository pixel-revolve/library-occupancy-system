package com.chy.service;

import com.chy.model.Result;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface AvatarService {

    /**
     * 上传头像
     */
    Result<String> upload(MultipartFile file);

    /**
     * 获取一个随机的默认头像
     */
    Result<String> getRandomAvatar();

}