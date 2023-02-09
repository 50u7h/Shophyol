package com.shophyol.admin.setting;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shophyol.common.entity.Setting;

public interface SettingRepository extends JpaRepository<Setting, String> {

}
