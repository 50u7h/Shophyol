package com.shophyol.admin.setting;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shophyol.common.entity.setting.Setting;
import com.shophyol.common.entity.setting.SettingCategory;

public interface SettingRepository extends JpaRepository<Setting, String> {

	public List<Setting> findByCategory(SettingCategory category);

}
