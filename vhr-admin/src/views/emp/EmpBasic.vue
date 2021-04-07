<template>
  <div>
    <!-- 面包屑导航栏 -->
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>员工资料</el-breadcrumb-item>
      <el-breadcrumb-item>基本资料</el-breadcrumb-item>
    </el-breadcrumb>
    <!-- 卡片区域 -->
    <el-card>
      <el-row :gutter="20">
        <el-col :span="6">
          <!-- 搜索与添加区域 -->
          <el-input
            placeholder="请输入员工姓名关键字"
            v-model="empname"
            clearable
            @clear="getEmployeeList"
          >
            <el-button
              slot="append"
              icon="el-icon-search"
              @click="getEmployeeList"
            ></el-button>
          </el-input>
        </el-col>
        <el-col :span="4">
          <el-button type="primary">高级查询</el-button>
        </el-col>
        <el-col :span="2" :offset="12">
          <el-button type="primary" @click="addEmp">添加员工</el-button>
        </el-col>
      </el-row>
      <!-- 用户列表数据 -->
      <el-table
        :data="emps"
        style="width: 100%"
        border
        stripe
        v-loading="loading"
        element-loading-text="正在加载..."
        element-loading-spinner="el-icon-loading"
        element-loading-background="rgba(0, 0, 0, 0.8)"
      >
        <el-table-column
          prop="name"
          label="姓名"
          fixed
          align="left"
          width="90"
        ></el-table-column>
        <el-table-column
          prop="workid"
          label="工号"
          align="left"
          width="85"
        ></el-table-column>
        <el-table-column
          prop="gender"
          label="性别"
          align="center"
          width="45"
        ></el-table-column>
        <el-table-column
          prop="birthday"
          label="出生日期"
          align="left"
          width="95"
        ></el-table-column>
        <el-table-column
          prop="idcard"
          label="身份证号码"
          align="left"
          width="150"
        ></el-table-column>
        <el-table-column
          prop="wedlock"
          label="婚姻状况"
          width="70"
          align="center"
        ></el-table-column>
        <el-table-column
          prop="nation.name"
          label="民族"
          width="50"
        ></el-table-column>
        <el-table-column
          prop="nativeplace"
          label="籍贯"
          width="80"
        ></el-table-column>
        <el-table-column
          prop="politicsstatus.name"
          label="政治面貌"
        ></el-table-column>
        <el-table-column
          prop="email"
          label="电子邮件"
          align="left"
          width="180"
        ></el-table-column>
        <el-table-column
          prop="phone"
          label="电话号码"
          align="left"
          width="100"
        ></el-table-column>
        <el-table-column
          prop="address"
          label="联系地址"
          align="left"
          width="220"
        ></el-table-column>
        <el-table-column
          prop="department.name"
          label="所属部门"
          align="left"
          width="75"
        ></el-table-column>
        <el-table-column
          prop="position.name"
          label="职位"
          width="100"
        ></el-table-column>
        <el-table-column
          prop="joblevel.name"
          label="职称"
          width="100"
        ></el-table-column>
        <el-table-column
          prop="engageform"
          label="聘用形式"
          align="left"
          width="100"
        ></el-table-column>
        <el-table-column
          prop="tiptopdegree"
          label="最高学历"
          align="left"
          width="80"
        ></el-table-column>
        <el-table-column
          prop="specialty"
          label="专业"
          align="left"
          width="150"
        ></el-table-column>
        <el-table-column
          prop="school"
          label="毕业院校"
          align="left"
          width="150"
        ></el-table-column>
        <el-table-column
          prop="begindate"
          label="入职日期"
          align="left"
          width="95"
        ></el-table-column>
        <el-table-column
          prop="conversiontime"
          label="转正日期"
          align="left"
          width="95"
        ></el-table-column>
        <el-table-column
          prop="begincontract"
          label="合同起始日期"
          align="left"
          width="95"
        ></el-table-column>
        <el-table-column
          prop="endcontract"
          label="合同截止日期"
          align="left"
          width="95"
        ></el-table-column>
        <el-table-column
          prop="begindate"
          label="合同期限"
          align="left"
          width="100"
        ></el-table-column>
        <el-table-column fixed="right" width="150" label="操作" align="center">
          <template slot-scope="scope">
            <el-button
              @click="showEditEmpView(scope.row)"
              type="primary"
              size="mini"
              >编辑</el-button
            >
            <el-button @click="deleteEmp(scope.row)" type="danger" size="mini"
              >删除</el-button
            >
          </template>
        </el-table-column>
      </el-table>
      <!-- 分页 -->
      <el-pagination
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page="page"
        :page-sizes="[10, 20, 30, 40]"
        :page-size="size"
        layout="total, sizes, prev, pager, next, jumper"
        :total="total"
      >
      </el-pagination>
    </el-card>
    <!-- 添加/修改员工信息的对话框 -->
    <el-dialog :title="title" :visible.sync="dialogVisible" width="70%">
      <!-- 内容主题区域 -->
      <el-form :model="empForm" :rules="empFormRules" ref="empFormRef">
        <el-row>
          <el-col :span="6">
            <el-form-item label="姓名" prop="name">
              <el-input
                v-model="empForm.name"
                size="mini"
                style="width: 150px"
                prefix-icon="el-icon-edit"
                placeholder="请输入员工姓名"
              ></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="5">
            <el-form-item label="性别" prop="gender">
              <el-radio-group v-model="empForm.gender">
                <el-radio label="男">男</el-radio>
                <el-radio label="女">女</el-radio>
              </el-radio-group>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="出生日期:" prop="birthday">
              <el-date-picker
                v-model="empForm.birthday"
                size="mini"
                type="date"
                value-format="yyyy-MM-dd"
                style="width: 150px"
                placeholder="出生日期"
              >
              </el-date-picker>
            </el-form-item>
          </el-col>
          <el-col :span="7">
            <el-form-item label="政治面貌:" prop="politicid">
              <el-select
                v-model="empForm.politicid"
                placeholder="政治面貌"
                size="mini"
                style="width: 200px"
              >
                <el-option
                  v-for="item in politicsstatus"
                  :key="item.id"
                  :label="item.name"
                  :value="item.id"
                >
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="6">
            <el-form-item label="民族:" prop="nationid">
              <el-select
                v-model="empForm.nationid"
                placeholder="民族"
                size="mini"
                style="width: 150px"
              >
                <el-option
                  v-for="item in nations"
                  :key="item.id"
                  :label="item.name"
                  :value="item.id"
                >
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="5">
            <el-form-item label="籍贯:" prop="nativeplace">
              <el-input
                size="mini"
                style="width: 120px"
                prefix-icon="el-icon-edit"
                v-model="empForm.nativeplace"
                placeholder="请输入籍贯"
              ></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="电子邮箱:" prop="email">
              <el-input
                size="mini"
                style="width: 150px"
                prefix-icon="el-icon-message"
                v-model="empForm.email"
                placeholder="请输入电子邮箱"
              ></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="7">
            <el-form-item label="联系地址:" prop="address">
              <el-input
                size="mini"
                style="width: 200px"
                prefix-icon="el-icon-edit"
                v-model="empForm.address"
                placeholder="请输入联系地址"
              ></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="6">
            <el-form-item label="职位:" prop="posid">
              <el-select
                v-model="empForm.posid"
                placeholder="职位"
                size="mini"
                style="width: 150px"
              >
                <el-option
                  v-for="item in positions"
                  :key="item.id"
                  :label="item.name"
                  :value="item.id"
                >
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="5">
            <el-form-item label="职称:" prop="joblevelid">
              <el-select
                v-model="empForm.joblevelid"
                placeholder="职称"
                size="mini"
                style="width: 150px"
              >
                <el-option
                  v-for="item in joblevels"
                  :key="item.id"
                  :label="item.name"
                  :value="item.id"
                >
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="所属部门:" prop="departmentid">
              <el-popover
                placement="right"
                title="请选择部门"
                width="200"
                trigger="hover"
                v-model="popVisible"
              >
                <el-tree
                  default-expand-all
                  :data="deps"
                  :props="defaultProps"
                  :expand-on-click-node="false"
                  @node-click="handleNodeClick"
                ></el-tree>
                <div
                  slot="reference"
                  style="
                    width: 150px;
                    display: inline-flex;
                    font-size: 13px;
                    border: 1px solid #dedede;
                    height: 26px;
                    border-radius: 5px;
                    cursor: pointer;
                    align-items: center;
                    padding-left: 8px;
                    box-sizing: border-box;
                  "
                  @click="showDepView"
                >
                  {{ inputDepName }}
                </div>
              </el-popover>
            </el-form-item>
          </el-col>
          <el-col :span="7">
            <el-form-item label="电话号码:" prop="phone">
              <el-input
                size="mini"
                style="width: 200px"
                prefix-icon="el-icon-phone"
                v-model="empForm.phone"
                placeholder="电话号码"
              ></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="6">
            <el-form-item label="工号:" prop="workid">
              <el-input
                size="mini"
                style="width: 150px"
                prefix-icon="el-icon-edit"
                v-model="maxWorkID"
                placeholder="工号"
                disabled
              ></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="5">
            <el-form-item label="学历:" prop="tiptopdegree">
              <el-select
                v-model="empForm.tiptopdegree"
                placeholder="学历"
                size="mini"
                style="width: 150px"
              >
                <el-option
                  v-for="item in tiptopDegrees"
                  :key="item"
                  :label="item"
                  :value="item"
                >
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="毕业院校:" prop="school">
              <el-input
                size="mini"
                style="width: 150px"
                prefix-icon="el-icon-edit"
                v-model="empForm.school"
                placeholder="毕业院校名称"
              ></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="7">
            <el-form-item label="专业名称:" prop="specialty">
              <el-input
                size="mini"
                style="width: 200px"
                prefix-icon="el-icon-edit"
                v-model="empForm.specialty"
                placeholder="请输入专业名称"
              ></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="6">
            <el-form-item label="入职日期:" prop="begindate">
              <el-date-picker
                v-model="empForm.begindate"
                size="mini"
                type="date"
                value-format="yyyy-MM-dd"
                style="width: 130px"
                placeholder="入职日期"
              >
              </el-date-picker>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="转正日期:" prop="conversiontime">
              <el-date-picker
                v-model="empForm.conversiontime"
                size="mini"
                type="date"
                value-format="yyyy-MM-dd"
                style="width: 130px"
                placeholder="转正日期"
              >
              </el-date-picker>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="合同起始日期:" prop="begincontract">
              <el-date-picker
                v-model="empForm.begincontract"
                size="mini"
                type="date"
                value-format="yyyy-MM-dd"
                style="width: 130px"
                placeholder="合同起始日期"
              >
              </el-date-picker>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="合同终止日期:" prop="endcontract">
              <el-date-picker
                v-model="empForm.endcontract"
                size="mini"
                type="date"
                value-format="yyyy-MM-dd"
                style="width: 150px"
                placeholder="合同终止日期"
              >
              </el-date-picker>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="8">
            <el-form-item label="身份证号码:" prop="idcard">
              <el-input
                size="mini"
                style="width: 180px"
                prefix-icon="el-icon-edit"
                v-model="empForm.idcard"
                placeholder="请输入身份证号码"
              ></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="聘用形式:" prop="engageform">
              <el-radio-group v-model="empForm.engageform">
                <el-radio label="劳动合同">劳动合同</el-radio>
                <el-radio label="劳务合同">劳务合同</el-radio>
              </el-radio-group>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="婚姻状况:" prop="wedlock">
              <el-radio-group v-model="empForm.wedlock">
                <el-radio label="已婚">已婚</el-radio>
                <el-radio label="未婚">未婚</el-radio>
                <el-radio label="离异">离异</el-radio>
              </el-radio-group>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <!-- 底部区域 -->
      <span slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="doAddEmp">确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
export default {
  data() {
    return {
      // 员工信息列表
      emps: [],
      // 员工列表总条数
      total: 0,
      // 当前页
      page: 1,
      // 每页条数
      size: 10,
      loading: false,
      empname: "",
      // 控制添加员工对话框的显示或隐藏
      dialogVisible: false,
      popVisible: false,
      defaultProps: {
        children: "children",
        label: "name"
      },
      // 消息框标题
      title: "",
      // 学历
      tiptopDegrees: [
        "本科",
        "大专",
        "硕士",
        "博士",
        "高中",
        "初中",
        "小学",
        "其他"
      ],
      // 部门
      deps: [],
      // 整治面貌
      politicsstatus: [],
      // 民族
      nations: [],
      // 职称
      joblevels: [],
      // 职位
      positions: [],
      // 工号
      maxWorkID: 0,
      inputDepName: "所属部门",
      // 员工信息数据
      empForm: {
        name: "javaboy",
        gender: "男",
        birthday: "1989-12-31",
        idcard: "610122199001011256",
        wedlock: "已婚",
        nationid: 1,
        nativeplace: "陕西",
        politicid: 13,
        email: "laowang@qq.com",
        phone: "18565558897",
        address: "深圳市南山区",
        departmentid: null,
        joblevelid: 9,
        posid: 29,
        engageform: "劳务合同",
        tiptopdegree: "本科",
        specialty: "信息管理与信息系统",
        school: "深圳大学",
        begindate: "2017-12-31",
        workstate: "在职",
        workid: "",
        contractterm: 2,
        conversiontime: "2018-03-31",
        // notworkdate: null,
        begincontract: "2017-12-31",
        endcontract: "2019-12-31",
        workage: null
      },
      // 员工信息校验
      empFormRules: {
        name: [{ required: true, message: "请输入用户名", trigger: "blur" }],
        gender: [{ required: true, message: "请输入性别", trigger: "blur" }],
        birthday: [
          { required: true, message: "请输入出生日期", trigger: "blur" }
        ],
        idcard: [
          { required: true, message: "请输入身份证号码", trigger: "blur" },
          {
            pattern: /(^[1-9]\d{5}(18|19|([23]\d))\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\d{3}[0-9Xx]$)|(^[1-9]\d{5}\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\d{2}$)/,
            message: "身份证号码格式不正确",
            trigger: "blur"
          }
        ],
        wedlock: [
          { required: true, message: "请输入婚姻状况", trigger: "blur" }
        ],
        nationid: [{ required: true, message: "请输入您组", trigger: "blur" }],
        nativeplace: [
          { required: true, message: "请输入籍贯", trigger: "blur" }
        ],
        politicid: [
          { required: true, message: "请输入政治面貌", trigger: "blur" }
        ],
        email: [
          { required: true, message: "请输入邮箱地址", trigger: "blur" },
          {
            type: "email",
            message: "邮箱格式不正确",
            trigger: "blur"
          }
        ],
        phone: [{ required: true, message: "请输入电话号码", trigger: "blur" }],
        address: [
          { required: true, message: "请输入员工地址", trigger: "blur" }
        ],
        departmentid: [
          { required: true, message: "请输入部门名称", trigger: "blur" }
        ],
        joblevelid: [
          { required: true, message: "请输入职称", trigger: "blur" }
        ],
        posid: [{ required: true, message: "请输入职位", trigger: "blur" }],
        engageForm: [
          { required: true, message: "请输入聘用形式", trigger: "blur" }
        ],
        tiptopdegree: [
          { required: true, message: "请输入学历", trigger: "blur" }
        ],
        specialty: [{ required: true, message: "请输入专业", trigger: "blur" }],
        school: [
          { required: true, message: "请输入毕业院校", trigger: "blur" }
        ],
        begindate: [
          { required: true, message: "请输入入职日期", trigger: "blur" }
        ],
        workstate: [
          { required: true, message: "请输入工作状态", trigger: "blur" }
        ],
        workid: [{ required: true, message: "请输入工号", trigger: "blur" }],
        contractterm: [
          { required: true, message: "请输入合同期限", trigger: "blur" }
        ],
        conversiontime: [
          { required: true, message: "请输入转正日期", trigger: "blur" }
        ],
        notworkdate: [
          { required: true, message: "请输入离职日期", trigger: "blur" }
        ],
        begincontract: [
          { required: true, message: "请输入合同起始日期", trigger: "blur" }
        ],
        endcontract: [
          { required: true, message: "请输入合同结束日期", trigger: "blur" }
        ],
        workage: [{ required: true, message: "请输入工龄", trigger: "blur" }]
      }
    };
  },
  mounted() {
    this.getEmployeeList();
  },
  methods: {
    getEmployeeList() {
      this.loading = true;
      let url =
        "/employee/basic/?currentPage=" + this.page + "&size=" + this.size;
      if (!this.empname == "") {
        url += "&name=" + this.empname;
      }
      this.getRequest(url).then(resp => {
        this.loading = false;
        this.emps = resp.data;
        this.total = resp.total;
        if (this.total > 0 && this.emps.length == 0) {
          this.page = 0;
          this.getEmployeeList();
        }
      });
    },
    // 监听pagesize改变事件
    handleSizeChange(newSize) {
      this.size = newSize;
      this.getEmployeeList();
    },
    // 监听页码值改变事件
    handleCurrentChange(newPage) {
      this.page = newPage;
      this.getEmployeeList();
    },
    // 获取所有部门
    getDepartments() {
      this.getRequest("/employee/basic/deps").then(resp => {
        this.deps = resp;
      });
    },
    // 获取所有整治面貌
    getPoliticsstatus() {
      this.getRequest("/employee/basic/politicsstatus").then(resp => {
        this.politicsstatus = resp;
      });
    },
    // 获取所有民族
    getNations() {
      this.getRequest("/employee/basic/nations").then(resp => {
        this.nations = resp;
      });
    },
    // 获取所有职称
    getJoblevels() {
      this.getRequest("/employee/basic/joblevels").then(resp => {
        this.joblevels = resp;
      });
    },
    // 获取所有职位
    getPositions() {
      this.getRequest("/employee/basic/positions").then(resp => {
        this.positions = resp;
      });
    },
    // 获取工号
    getMaxWorkID() {
      this.getRequest("/employee/basic/maxWorkID").then(resp => {
        this.maxWorkID = resp.obj;
      });
    },
    // 准备添加新员工
    addEmp() {
      this.dialogVisible = true;
      this.title = "添加新员工";
      this.getDepartments();
      this.getPoliticsstatus();
      this.getNations();
      this.getJoblevels();
      this.getPositions();
      this.getMaxWorkID();
    },
    handleNodeClick(data) {
      this.inputDepName = data.name;
      this.empForm.departmentid = data.id;
      this.popVisible = !this.popVisible;
    },
    showDepView() {
      this.popVisible = !this.popVisible;
    },
    // 添加员工
    doAddEmp() {
      if (this.title === "添加新员工") {
        this.empForm.workid = this.maxWorkID;
        this.$refs.empFormRef.validate(valid => {
          if (!valid) return;
          this.postRequest(
            "/employee/basic/",
            this.$qs.stringify(this.empForm)
          ).then(resp => {
            if (resp) {
              this.dialogVisible = false;
              this.getEmployeeList();
            }
          });
        });
      } else {
        this.$refs.empFormRef.validate(valid => {
          if (!valid) return;
          this.putRequest(
            "/employee/basic/",
            this.$qs.stringify(this.empForm)
          ).then(resp => {
            if (resp) {
              this.dialogVisible = false;
              this.getEmployeeList();
            }
          });
        });
      }
    },
    // 删除员工
    deleteEmp(data) {
      this.$confirm(
        "此操作将永久删除【" + data.name + "】, 是否继续?",
        "提示",
        {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }
      )
        .then(() => {
          this.deleteRequest("/employee/basic/" + data.id).then(resp => {
            if (resp) {
              this.getEmployeeList();
            }
          });
        })
        .catch(() => {
          this.$message({
            type: "info",
            message: "已取消删除"
          });
        });
    },
    // 编辑员工信息
    showEditEmpView(data) {
      this.title = "编辑员工信息";
      this.dialogVisible = true;
      this.getDepartments();
      this.getPoliticsstatus();
      this.getNations();
      this.getJoblevels();
      this.getPositions();
      this.empForm.id = data.id;
      this.empForm.name = data.name;
      this.empForm.gender = data.gender;
      this.empForm.birthday = data.birthday;
      this.empForm.idcard = data.idcard;
      this.empForm.wedlock = data.wedlock;
      this.empForm.nationid = data.nationid;
      this.empForm.nativeplace = data.nativeplace;
      this.empForm.politicid = data.politicid;
      this.empForm.email = data.email;
      this.empForm.phone = data.phone;
      this.empForm.address = data.address;
      this.empForm.departmentid = data.departmentid;
      this.empForm.joblevelid = data.joblevelid;
      this.empForm.posid = data.posid;
      this.empForm.engageform = data.engageform;
      this.empForm.tiptopdegree = data.tiptopdegree;
      this.empForm.specialty = data.specialty;
      this.empForm.school = data.school;
      this.empForm.begindate = data.begindate;
      this.empForm.workstate = data.workstate;
      this.empForm.workid = data.workid;
      this.empForm.contractterm = data.contractterm;
      this.empForm.conversiontime = data.conversiontime;
      this.empForm.begincontract = data.begincontract;
      this.empForm.endcontract = data.endcontract;
      this.empForm.workage = data.workage;
      this.initEmpXiangguanxinxi(data);
      this.inputDepName = data.department.name;
    },
    initEmpXiangguanxinxi(data) {
      this.empForm.nationid = data.nation.id;
      this.empForm.politicid = data.politicsstatus.id;
      this.empForm.joblevelid = data.joblevel.id;
      this.empForm.departmentid = data.department.id;
      this.maxWorkID = data.workid;
    }
  }
};
</script>

<style>
</style>