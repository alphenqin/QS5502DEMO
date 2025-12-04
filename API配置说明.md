# API配置说明

## 1. 配置服务器地址

在 `app/src/main/java/com/qs/qs5502demo/api/ApiConfig.java` 文件中配置服务器地址：

```java
// WMS接口基础地址
public static final String WMS_BASE_URL = "http://wms-server.example.com/api";

// AGV调度系统接口基础地址
public static final String AGV_BASE_URL = "http://agv-dispatch.example.com/api";
```

**请根据实际部署环境修改以上地址。**

## 2. 已实现的接口

### WMS接口（WmsApiService）
- ✅ 登录接口：`POST /api/auth/login`
- ✅ 托盘扫码接口：`POST /api/pallet/scan`
- ✅ 阀门绑定接口：`POST /api/valve/bind`
- ✅ 阀门查询接口：`POST /api/valve/query`
- ✅ 任务记录查询接口：`POST /api/task/query`

### AGV调度系统接口（AgvApiService）
- ✅ 入库：呼叫入库 `POST /api/agv/inbound/call`
- ✅ 送检：呼叫送检 `POST /api/agv/inspection/send`
- ✅ 送检：空托回库 `POST /api/agv/pallet/returnFromInspection`
- ✅ 回库：呼叫托盘 `POST /api/agv/pallet/callToInspection`
- ✅ 回库：阀门回库 `POST /api/agv/valve/returnToWarehouse`
- ✅ 出库：呼叫出库 `POST /api/agv/outbound/call`
- ✅ 出库：空托回库 `POST /api/agv/pallet/returnFromSwap`
- ✅ 取消任务 `POST /api/agv/task/cancel`

## 3. 已更新的功能模块

### 登录模块
- ✅ 登录页面（LoginActivity）
- ✅ Token保存和管理（PreferenceUtil）
- ✅ 主界面登录检查

### 入库模块
- ✅ InboundActivity - 已更新使用新API
- ✅ BindValveActivity - 已更新使用新API

### 其他模块
- ✅ SendInspectionActivity（送检）- 已更新使用新API
- ✅ ReturnWarehouseActivity（回库）- 已更新使用新API
- ✅ OutboundActivity（出库）- 已更新使用新API
- ✅ TaskManageActivity（任务管理）- 已更新使用新API
- ✅ SelectValveActivity（选阀门）- 已更新使用新API

## 4. 数据模型更新

已更新的数据模型：
- ✅ Pallet - 添加了 `binCode` 字段
- ✅ Valve - 添加了 `matCode`、`binCode`、`valveStatus` 字段
- ✅ Task - 添加了 `outID`、`matCode`、`binCode` 字段

## 5. 使用示例

### 登录
```java
WmsApiService wmsApiService = new WmsApiService(context);
LoginRequest request = new LoginRequest();
request.setUsername("pda001");
request.setPassword("123456");
request.setDeviceCode("PDA-01");
LoginResponse response = wmsApiService.login(request);
```

### 托盘扫码
```java
Pallet pallet = wmsApiService.scanPallet(barcode, context);
```

### 呼叫入库
```java
AgvApiService agvApiService = new AgvApiService();
Map<String, String> params = new HashMap<>();
params.put("palletNo", "11-01");
params.put("palletType", "SMALL");
params.put("swapStation", "WAREHOUSE_SWAP_1");
params.put("binCode", "2-01");
params.put("matCode", "MAT-DN50-001");
params.put("operator", "张三");
TaskResponse response = agvApiService.callInbound(params, context);
```

## 6. 注意事项

1. **网络权限**：确保 AndroidManifest.xml 中已添加网络权限
2. **Token管理**：Token会自动添加到请求头中，无需手动处理
3. **错误处理**：所有API调用都在后台线程执行，需要在主线程更新UI
4. **服务器地址**：部署前务必修改 ApiConfig.java 中的服务器地址

## 7. 已完成的工作

✅ 所有Activity已更新完成：
- ✅ InboundActivity（入库）
- ✅ BindValveActivity（阀门绑定）
- ✅ SendInspectionActivity（送检）
- ✅ ReturnWarehouseActivity（回库）
- ✅ OutboundActivity（出库）
- ✅ TaskManageActivity（任务管理）
- ✅ SelectValveActivity（选阀门）

所有Activity都已：
1. ✅ 将 `ApiClient` 替换为 `WmsApiService` 和 `AgvApiService`
2. ✅ 使用 `binCode` 替代 `locationCode`
3. ✅ 使用 `outID` 替代 `taskNo`
4. ✅ 在后台线程执行网络请求，主线程更新UI
5. ✅ 添加了分页参数支持
6. ✅ 根据任务类型自动筛选阀门状态

