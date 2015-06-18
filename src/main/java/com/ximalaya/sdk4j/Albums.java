package com.ximalaya.sdk4j;

import java.util.ArrayList;
import java.util.List;

import com.ximalaya.sdk4j.http.HttpParameter;
import com.ximalaya.sdk4j.model.DTOValidateUtil;
import com.ximalaya.sdk4j.model.Paging;
import com.ximalaya.sdk4j.model.XimalayaException;
import com.ximalaya.sdk4j.model.dto.album.Album;
import com.ximalaya.sdk4j.model.dto.album.AlbumList;
import com.ximalaya.sdk4j.model.dto.album.AlbumTracks;
import com.ximalaya.sdk4j.util.StringUtil;

/**
 * 专辑相关接口
 * @author will
 *
 */
public class Albums extends Ximalaya {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8651526987345601726L;
	
	private static final List<Album> EMPTY_ALBUMS = new ArrayList<Album> (0);
	
	/**
	 * 根据分类和标签获取热门专辑（带分页）
	 * 
	 * @param categoryID 分类ID，必填，如果为0则表示所有分类下热门专辑
	 * @param tagName    标签名，可选
	 * @param paging     分页参数，可选，不填则为默认值
	 * @return
	 * @throws XimalayaException 
	 */
	public AlbumList getHotAlbumList(long categoryID, String tagName, Paging paging) throws XimalayaException {
		DTOValidateUtil.validateCategoryID(categoryID);
		paging = paging == null ? new Paging(): paging;
		
		HttpParameter[] specificParams = null;
		if(!StringUtil.isEmpty(tagName)) {
			specificParams = new HttpParameter[4];
			specificParams[0] = new HttpParameter("category_id", categoryID);
			specificParams[1] = new HttpParameter("tag_name", tagName);
			specificParams[2] = new HttpParameter("page", paging.getPage());
			specificParams[3] = new HttpParameter("count", paging.getCount());
		}
		else {
			specificParams = new HttpParameter[3];
			specificParams[0] = new HttpParameter("category_id", categoryID);
			specificParams[1] = new HttpParameter("page", paging.getPage());
			specificParams[2] = new HttpParameter("count", paging.getCount());
		}
		return Album.constructAlbumList(
				CLIENT.get(String.format("%s/albums/hot", BASE_URL), 
							assembleHttpParams(specificParams)));
	}
	
	/**
	 * 根据一组ID批量获取专辑
	 * 
	 * @param albumIDs 一组专辑ID，必填
	 * @return
	 * @throws XimalayaException
	 */
	public List<Album> batchGetAlbums(long[] albumIDs) throws XimalayaException {
		if(albumIDs == null || albumIDs.length == 0) {
			return EMPTY_ALBUMS; 
		}
		
		HttpParameter[] specificParams = new HttpParameter[] { new HttpParameter("ids", StringUtil.join(albumIDs, ",")) };
		return Album.constructAlbums(
				CLIENT.get(String.format("%s/albums/get_batch", BASE_URL),
						   assembleHttpParams(specificParams)));
	}
	
	/**
	 * 根据专辑ID获取专辑内声音（即浏览专辑）
	 * 
	 * @param albumID 专辑ID
	 * @param paging
	 * @return
	 * @throws XimalayaException 
	 */
	public AlbumTracks browseAlbumTracks(long albumID, Paging paging) throws XimalayaException {
		DTOValidateUtil.validateAlbumID(albumID);
		paging = paging == null ? new Paging(): paging;
		
		HttpParameter[] specificParams = new HttpParameter[3];
		specificParams[0] = new HttpParameter("album_id", albumID);
		specificParams[1] = new HttpParameter("page", paging.getPage());
		specificParams[2] = new HttpParameter("count", paging.getCount());
		return Album.constructAlbumTracks(
				CLIENT.get(String.format("%s/albums/browse", BASE_URL),
						   assembleHttpParams(specificParams)));
	}
	
}