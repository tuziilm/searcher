package com.tuziilm.searcher.mvc;

import com.tuziilm.searcher.common.LoginContext;
import com.tuziilm.searcher.common.OperationLogType;
import com.tuziilm.searcher.common.Paginator;
import com.tuziilm.searcher.common.Query.NameQuery;
import com.tuziilm.searcher.common.RemarkForm;
import com.tuziilm.searcher.common.UpLoads;
import com.tuziilm.searcher.common.UploadType;
import com.tuziilm.searcher.domain.App;
import com.tuziilm.searcher.domain.OperationLog;
import com.tuziilm.searcher.exception.UploadException;
import com.tuziilm.searcher.mvc.AppController.AppForm;
import com.tuziilm.searcher.mvc.annotation.Ids;
import com.tuziilm.searcher.service.AppService;
import com.tuziilm.searcher.service.OperationLogService;
import com.tuziilm.searcher.service.SysUserService;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;

/**
 * App管理
 * Author: <a href="tuziilm@163.com">Tuziilm</a>
 * Date: 15-6-2
 * Time: 上午9:51
 */
@Controller
@RequestMapping("/app/app_manager")
public class AppController extends CRUDController<App,AppService,AppForm, NameQuery>{
    private final Logger log= LoggerFactory.getLogger(getClass());
    public AppController() {
        super("app/app_manager");
    }

    @Resource
    private SysUserService sysUserService;
    @Resource
    private OperationLogService operationLogService;
    @Resource
    public void setAppService(AppService appService){
        this.service = appService;
    }

    @Override
    public void innerSave(AppForm form, BindingResult errors, Model model,
                          HttpServletRequest request, HttpServletResponse response) {
        App app = form.toObj();
        app.setUid(LoginContext.getUid());
        try {
            if (!form.imgFile.isEmpty()) {
                uploadImg(app, form.imgFile);
            }
            service.saveOrUpdate(app);
            operationLogService.save(OperationLog.valueOf(OperationLogType.APP, String.format("%sAPP[ID:%s, 名称:%s]", app.getId() == null ? "新增" : "修改", app.getId(), app.getName())));
        } catch (UploadException e) {
            errors.addError(new ObjectError("upload", e.getMessage()));
        } catch (DuplicateKeyException e){
            errors.addError(new ObjectError("database", "不能上传名称，链接地址和类型完全一样的数据"));
        }
    }

    @Override
    protected void postList(int page, Paginator paginator, NameQuery query, Model model) {
        model.addAttribute("users", sysUserService.getAllSysUsersMapCache());
        super.postList(page, paginator, query, model);
    }

    private void uploadImg(App app, MultipartFile linkFile) throws UploadException{
        String linkPath=UpLoads.upload(linkFile, UploadType.PIC);
        app.setImgPath(linkPath);
        app.setImgFileName(linkFile.getOriginalFilename());
    }
    @Override
    public String delete(@Ids("ids") int[] ids, HttpServletRequest request) {
        String page = super.delete(ids, request);
        operationLogService.save(OperationLog.valueOf(OperationLogType.APP, String.format("删除App[ID:%s]", Arrays.toString(ids))));
        return page;
    }
    //唯一性索引查询
    public static class AppUniqueKeyQuery extends com.tuziilm.searcher.common.Query {
        private String name;
        private String link;
        private Integer type;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
            this.addItem("name",name);
        }

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
            this.addItem("link",link);
        }

        public Integer getType() {
            return type;
        }

        public void setType(Integer type) {
            this.type = type;
            this.addItem("type",type);
        }
    }

    public static class AppForm extends RemarkForm<App> {
        @NotBlank(message = "名称不能为空")
        private String name;
        private MultipartFile imgFile;
        @URL(message = "链接格式不合法")
        @NotBlank(message = "链接地址不能为空")
        private String link;
        private Integer type;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public MultipartFile getImgFile() {
            return imgFile;
        }

        public void setImgFile(MultipartFile imgFile) {
            this.imgFile = imgFile;
        }

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }

        public Integer getType() {
            return type;
        }

        public void setType(Integer type) {
            this.type = type;
        }

        @Override
        public App newObj() {
            return new App();
        }

        @Override
        public void populateObj(App app) {
            app.setName(name);
            app.setLink(link);
            app.setType(type);
            app.setStatus(0);
        }
    }

}
