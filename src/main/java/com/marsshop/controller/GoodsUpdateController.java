package com.marsshop.controller;

import com.marsshop.domain.Goods;
import com.marsshop.domain.Goodstype;
import com.marsshop.service.GoodsService;
import com.marsshop.service.GoodstypeService;
import com.marsshop.service.Impl.GoodsServiceImpl;
import com.marsshop.service.Impl.GoodstypeServiceImpl;
import org.apache.commons.fileupload.*;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletRequestContext;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

@WebServlet(name = "GoodsUpdateController", value = "/admin/goods/update")
public class GoodsUpdateController extends HttpServlet {
    private GoodsService goodsService = new GoodsServiceImpl();
    private GoodstypeService goodstypeService = new GoodstypeServiceImpl();
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 目的是为了包装 HttpServletRequest 对象，以便在文件上传过程中更方便地处理请求的内容。
        // RequestContext 提供了对请求内容的访问，是 Commons FileUpload 库中用于处理文件上传的一部分。
        RequestContext requestContext = new ServletRequestContext(request);
        // 判断提交的编码方式是否有multipart/form-data，即是否包含文件上传的数据
        if (FileUpload.isMultipartContent(requestContext)) {

            // 创建了一个文件上传的处理器，使用磁盘作为中间存储区，能够有效地处理大型文件上传而不会占用过多内存。
            // 将FileItem对象创建在磁盘上而不是内存中，这对于处理大型文件上传是有效的。
            FileItemFactory fileItemFactory = new DiskFileItemFactory();
            // 用于处理文件上传的整个过程，提供了解析HTTP请求、提取表单字段和文件等功能。
            FileUpload upload = new FileUpload(fileItemFactory);

            Goods goods = new Goods();
            // 解析请求，获得所有上传数据
            try {
                // 每次进入添加商品页面时都要查询一下商品种类列表
                List<Goodstype> goodstypeList = goodstypeService.selectAll();
                request.setAttribute("typeList", goodstypeList);

                // 限制上传文件不超过2M
                upload.setFileSizeMax(2 * 1024 * 1024);

                List<FileItem> fileItems = upload.parseRequest(requestContext);
                // FileItem代表了表单中的一个字段（可以是普通表单字段或文件上传字段）
                for (FileItem fileItem : fileItems) {
                    // 表单域
                    if (fileItem.isFormField()) {
                        String fName = fileItem.getFieldName(); //字段名
                        String value = fileItem.getString("UTF-8"); //值
                        switch (fName) {
                            case "gdName":
                                goods.setGdName(value);
                                break;
                            case "tid":
                                Goodstype goodstype = new Goodstype();
                                goodstype.setTid(Integer.parseInt(value));
                                goods.setType(goodstype);
                                break;
                            case "gdCode":
                                goods.setGdCode(value);
                                break;
                            case "gdPrice":
                                goods.setGdPrice(new BigDecimal(value));
                                break;
                            case "gdQuantity":
                                goods.setGdQuantity(Integer.parseInt(value));
                            case "gdCity":
                                goods.setGdCity(value);
                                break;
                            case "gdInfo":
                                goods.setGdInfo(value);
                                break;
                            case "gdId":
                                goods.setGdId(Integer.parseInt(value));
                                break;
                        }
                    } else { //上传的文件
                        // 很奇怪，不选上传文件也会进入到这个地方
                        if (fileItem.getSize() < 1) {
                            continue;
                        }

                        // 定义上传的目录
                        File dir = new File("/var/lib/tomcat9/webapps/MarsShop/upload");
                        // 如果目录不存在，创建（只要磁盘名存在，后面的目录都会被创建）
                        if (!dir.exists()) {
                            dir.mkdirs();
                        }
                        // 获取上传文件的名称，解析获得后缀名
                        String oldFileName = fileItem.getName();
                        String fileExtension = oldFileName.substring(oldFileName.lastIndexOf("."));
                        // 生成一个新的文件名，名称规则为yyMMddHHmmssSSS + 三位流水号（随机数）
                        Date date = new Date();
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyMMddHHmmssSSS");
                        String newFileName = simpleDateFormat.format(date);
                        Random random = new Random();
                        newFileName += random.nextInt(1000 - 99) + 100; // 100-999
                        newFileName += fileExtension;
                        // 上传文件
                        fileItem.write(new File(dir, newFileName));
                        // 把文件名添加到实体对象中
                        goods.setGdImage(newFileName);
                    }
                }

                request.setAttribute("goods", goods);

                int result = goodsService.update(goods);
                if (result == -1) {
                    request.setAttribute("msg", "商品名称已存在！");
                    request.getRequestDispatcher("/admin/goods-edit.jsp").forward(request, response);
                } else if (result == -2) {
                    request.setAttribute("msg", "商品货号已存在！");
                    request.getRequestDispatcher("/admin/goods-edit.jsp").forward(request, response);
                } else if (result == 1) {
                    HttpSession session = request.getSession();
                    session.setAttribute("msg", "商品信息更新成功！");
                    response.sendRedirect(request.getContextPath() + "/admin/goods");
                }

            } catch (FileUploadBase.FileSizeLimitExceededException e) {
                e.printStackTrace();
                request.setAttribute("msg", "图片大小超过2M，无法上传！！");
                request.getRequestDispatcher("/admin/goods-edit.jsp").forward(request, response);
            } catch (Exception e) {
                e.printStackTrace();
                request.setAttribute("msg", "商品信息更新失败！");
                request.getRequestDispatcher("/admin/goods-edit.jsp").forward(request, response);
            }
        } else {
            request.setAttribute("msg", "请设置表单的enctype的值位multipart/form-data");
        }

    }
}
