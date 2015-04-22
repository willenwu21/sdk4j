package com.ximalaya.sdk4j.model.dto.live;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.ximalaya.sdk4j.http.HttpResponse;
import com.ximalaya.sdk4j.model.XimalayaException;
import com.ximalaya.sdk4j.model.XimalayaResponse;
import com.ximalaya.sdk4j.model.dto.IKindAware;
import com.ximalaya.sdk4j.model.dto.profile.User;

/**
 * 直播节目DTO
 * 
 * @author will
 *
 */
public class Program extends XimalayaResponse implements IKindAware {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6614882882187524681L;
	
	private Long id;                    // 节目ID
	private Long scheduleID;            // 节目时间表ID
	private String programName;         // 节目名称
	private String startTime;           // 节目开始时间
	private String endTime;             // 节目结束时间
	private Integer playType;           // 播放类型，0-直播，1-重播，2-跨天，3-无流期
	private String backPicUrl;          // 节目背景图URL
	private List<User> announcers;      // 主播列表
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getScheduleID() {
		return scheduleID;
	}
	public void setScheduleID(Long scheduleID) {
		this.scheduleID = scheduleID;
	}
	public String getProgramName() {
		return programName;
	}
	public void setProgramName(String programName) {
		this.programName = programName;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public Integer getPlayType() {
		return playType;
	}
	public void setPlayType(Integer playType) {
		this.playType = playType;
	}
	public String getBackPicUrl() {
		return backPicUrl;
	}
	public void setBackPicUrl(String backPicUrl) {
		this.backPicUrl = backPicUrl;
	}
	public List<User> getAnnouncers() {
		return announcers;
	}
	public void setAnnouncers(List<User> announcers) {
		this.announcers = announcers;
	}

	@Override
	public String getKind() {
		return "program";
	}
	
	public Program(HttpResponse response) throws XimalayaException {
		super(response);
		init(response.asJSONObject());
	}
	
	private void init(JSONObject json) throws XimalayaException {
		if(json != null) {
			try {
				id = json.getLong("id");
				scheduleID = json.getLong("schedule_id");
				programName = json.getString("program_name");
				startTime = json.getString("start_time");
				endTime = json.getString("end_time");
				playType = json.getInt("play_type");
				backPicUrl = json.getString("back_pic_url");
				List<User> announcers = new ArrayList<User> ();
				JSONArray announcersJsonArray = json.getJSONArray("announcers");
				int size = announcersJsonArray.length();
				for(int i = 0; i < size; i++) {
					announcers.add(new User(announcersJsonArray.getJSONObject(i)));
				}
				this.announcers = announcers;
			} catch (JSONException jsone) {
				throw new XimalayaException(jsone.getMessage() + ":" + json.toString(), jsone);
			}
		}
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(this == obj) {
			return true;
		}
		if(obj == null) {
			return false;
		}
		if(getClass() != obj.getClass()) {
			return false;
		}
		
		Program other = (Program) obj;
		if((id == null && other.id != null) 
			|| !id.equals(other.id)) {
			return false;
		}
		
		return true;
	}
	
	@Override
	public String toString() {
		StringBuilder strBuilder = new StringBuilder();
		strBuilder.append("Program {id: ");
		strBuilder.append(id);
		strBuilder.append(", scheduleID: ");
		strBuilder.append(scheduleID);
		strBuilder.append(", programName: \"");
		strBuilder.append(programName);
		strBuilder.append("\", startTime: \"");
		strBuilder.append(startTime);
		strBuilder.append("\", endTime: \"");
		strBuilder.append(endTime);
		strBuilder.append("\", playType: ");
		strBuilder.append(playType);
		strBuilder.append(", backPicUrl: \"");
		strBuilder.append(backPicUrl);
		strBuilder.append("\", announcers: [");
		if(announcers != null && !announcers.isEmpty()) {
			for(User announcer: announcers) {
				strBuilder.append(announcer.toString());
				strBuilder.append(", ");
			}
		}
		strBuilder.append("]");
		strBuilder.append("}");
		return strBuilder.toString();
	}
}