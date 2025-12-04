# PDA系统API接口文档

## 说明

本文档定义了PDA系统与WMS系统交互所需的RESTful API接口。PDA通过HTTP请求调用WMS提供的API接口进行数据交互。

---

## 基础信息

### 服务器地址
```
基础URL: http://your-wms-server.com/api
```

### 请求头
所有请求需要包含以下请求头：
```
Content-Type: application/json
Accept: application/json
```

### 认证方式
根据实际需求添加认证信息（如Token、API Key等），建议在请求头中添加：
```
Authorization: Bearer {token}
```

### 统一响应格式

所有API接口采用统一的响应格式：

#### 成功响应
```json
{
  "code": 200,
  "message": "操作成功",
  "data": {
    // 具体数据内容
  }
}
```

#### 失败响应
```json
{
  "code": 400,
  "message": "错误信息描述",
  "data": null
}
```

### 响应状态码说明

| 状态码 | 说明 |
|--------|------|
| 200 | 请求成功 |
| 400 | 请求参数错误 |
| 401 | 未授权，需要登录 |
| 403 | 禁止访问 |
| 404 | 资源不存在 |
| 500 | 服务器内部错误 |

---

## 1. 托盘扫码接口

### 接口说明
根据托盘条码/二维码获取托盘信息。

### 请求信息

- **请求方法**：`POST`
- **请求路径**：`/api/pallet/scan`
- **请求头**：`Content-Type: application/json`

### 请求参数

| 参数名 | 类型 | 是否必填 | 说明 | 示例值 |
|--------|------|----------|------|--------|
| barcode | String | 是 | 托盘二维码或条码 | "PALLET-QR-CODE-11-01" |

### 请求示例

```json
{
  "barcode": "PALLET-QR-CODE-11-01"
}
```

### 响应参数

| 参数名 | 类型 | 说明 | 示例值 |
|--------|------|------|--------|
| palletNo | String | 托盘编号 | "11-01" |
| palletType | String | 托盘型号：SMALL(小托盘) / LARGE(大托盘) | "SMALL" |
| swapStation | String | 置换区站点 | "1-SMALL" |
| locationCode | String | 库位号 | "2-01" |

### 响应示例

#### 成功响应
```json
{
  "code": 200,
  "message": "操作成功",
  "data": {
    "palletNo": "11-01",
    "palletType": "SMALL",
    "swapStation": "1-SMALL",
    "locationCode": "2-01"
  }
}
```

#### 失败响应
```json
{
  "code": 400,
  "message": "未找到对应的托盘信息",
  "data": null
}
```

---

## 2. 阀门绑定接口

### 接口说明
将阀门信息绑定到托盘，建立阀门与托盘的关联关系。

### 请求信息

- **请求方法**：`POST`
- **请求路径**：`/api/valve/bind`
- **请求头**：`Content-Type: application/json`

### 请求参数

| 参数名 | 类型 | 是否必填 | 说明 | 示例值 |
|--------|------|----------|------|--------|
| valveNo | String | 是 | 阀门编号，唯一标识 | "V20250101-001" |
| valveModel | String | 是 | 阀门型号 | "DN50" |
| vendorName | String | 是 | 厂家名称 | "XX阀门厂" |
| inboundDate | String | 是 | 入库日期，格式：yyyy-MM-dd | "2025-01-15" |
| palletNo | String | 是 | 托盘号 | "11-01" |
| locationCode | String | 是 | 库位号 | "2-01" |

### 请求示例

```json
{
  "valveNo": "V20250101-001",
  "valveModel": "DN50",
  "vendorName": "XX阀门厂",
  "inboundDate": "2025-01-15",
  "palletNo": "11-01",
  "locationCode": "2-01"
}
```

### 响应参数

| 参数名 | 类型 | 说明 | 示例值 |
|--------|------|------|--------|
| success | Boolean | 是否成功 | true |

### 响应示例

#### 成功响应
```json
{
  "code": 200,
  "message": "绑定成功",
  "data": {
    "success": true
  }
}
```

#### 失败响应
```json
{
  "code": 400,
  "message": "阀门编号已存在",
  "data": {
    "success": false
  }
}
```

---

## 3. 查询阀门接口

### 接口说明
根据条件查询阀门信息列表，支持多条件组合查询。

### 请求信息

- **请求方法**：`POST`
- **请求路径**：`/api/valve/query`
- **请求头**：`Content-Type: application/json`

### 请求参数

| 参数名 | 类型 | 是否必填 | 说明 | 示例值 |
|--------|------|----------|------|--------|
| vendorName | String | 否 | 厂家名称（支持模糊查询） | "XX阀门厂" |
| valveNo | String | 否 | 阀门编号（精确查询） | "V20250101-001" |
| valveModel | String | 否 | 阀门型号（支持模糊查询） | "DN50" |
| inboundDate | String | 否 | 入库日期，格式：yyyy-MM-dd | "2025-01-15" |
| valveStatus | String | 否 | 阀门状态：IN_STOCK(在库) / IN_INSPECTION(检测中) / INSPECTED(已检测) / OUTBOUND(已出库) | "IN_STOCK" |
| taskType | String | 否 | 任务类型，用于限制查询范围：SEND_INSPECTION(送检) / OUTBOUND(出库) / RETURN(回库) | "SEND_INSPECTION" |

### 请求示例

```json
{
  "vendorName": "XX阀门厂",
  "valveModel": "DN50",
  "inboundDate": "2025-01-15",
  "valveStatus": "IN_STOCK"
}
```

### 响应参数

| 参数名 | 类型 | 说明 | 示例值 |
|--------|------|------|--------|
| list | Array | 阀门列表 | - |
| list[].valveNo | String | 阀门编号 | "V20250101-001" |
| list[].valveModel | String | 阀门型号 | "DN50" |
| list[].vendorName | String | 厂家名称 | "XX阀门厂" |
| list[].inboundDate | String | 入库日期 | "2025-01-15" |
| list[].palletNo | String | 托盘号 | "11-01" |
| list[].locationCode | String | 库位号 | "2-01" |
| list[].valveStatus | String | 阀门状态 | "IN_STOCK" |
| total | Integer | 总记录数 | 10 |

### 响应示例

#### 成功响应
```json
{
  "code": 200,
  "message": "查询成功",
  "data": {
    "list": [
      {
        "valveNo": "V20250101-001",
        "valveModel": "DN50",
        "vendorName": "XX阀门厂",
        "inboundDate": "2025-01-15",
        "palletNo": "11-01",
        "locationCode": "2-01",
        "valveStatus": "IN_STOCK"
      },
      {
        "valveNo": "V20250101-002",
        "valveModel": "DN50",
        "vendorName": "XX阀门厂",
        "inboundDate": "2025-01-15",
        "palletNo": "11-02",
        "locationCode": "2-02",
        "valveStatus": "IN_STOCK"
      }
    ],
    "total": 2
  }
}
```

#### 无数据响应
```json
{
  "code": 200,
  "message": "查询成功",
  "data": {
    "list": [],
    "total": 0
  }
}
```

---

## 4. 创建任务接口

### 接口说明
创建AGV任务，包括入库、送检、回库、出库等任务类型。

### 请求信息

- **请求方法**：`POST`
- **请求路径**：`/api/task/create`
- **请求头**：`Content-Type: application/json`

### 请求参数

| 参数名 | 类型 | 是否必填 | 说明 | 示例值 |
|--------|------|----------|------|--------|
| taskType | String | 是 | 任务类型：INBOUND(入库) / SEND_INSPECTION(送检) / RETURN(回库) / OUTBOUND(出库) | "INBOUND" |
| palletNo | String | 是 | 托盘号 | "11-01" |
| locationCode | String | 是 | 库位号 | "2-01" |
| fromStation | String | 是 | 起始站点 | "WAREHOUSE_SWAP_1" |
| toStation | String | 是 | 目标站点 | "WAREHOUSE_LOCATION_2_01" |
| valveNo | String | 否 | 阀门编号（可选） | "V20250101-001" |
| operator | String | 否 | 操作员 | "张三" |

### 请求示例

#### 入库任务
```json
{
  "taskType": "INBOUND",
  "palletNo": "11-01",
  "locationCode": "2-01",
  "fromStation": "WAREHOUSE_SWAP_1",
  "toStation": "WAREHOUSE_LOCATION_2_01",
  "operator": "张三"
}
```

#### 送检任务
```json
{
  "taskType": "SEND_INSPECTION",
  "palletNo": "11-01",
  "locationCode": "2-01",
  "fromStation": "WAREHOUSE_LOCATION_2_01",
  "toStation": "INSPECTION_STATION",
  "valveNo": "V20250101-001",
  "operator": "李四"
}
```

#### 回库任务
```json
{
  "taskType": "RETURN",
  "palletNo": "11-01",
  "locationCode": "2-01",
  "fromStation": "INSPECTION_STATION",
  "toStation": "WAREHOUSE_LOCATION_2_01",
  "valveNo": "V20250101-001",
  "operator": "王五"
}
```

#### 出库任务
```json
{
  "taskType": "OUTBOUND",
  "palletNo": "11-01",
  "locationCode": "2-01",
  "fromStation": "WAREHOUSE_LOCATION_2_01",
  "toStation": "WAREHOUSE_SWAP_1",
  "valveNo": "V20250101-001",
  "operator": "赵六"
}
```

### 响应参数

| 参数名 | 类型 | 说明 | 示例值 |
|--------|------|------|--------|
| taskNo | String | 任务编号 | "R20250715145830999" |
| taskType | String | 任务类型 | "INBOUND" |
| status | String | 任务状态 | "PENDING" |
| createTime | String | 创建时间 | "2025-01-15 14:58:30" |

### 响应示例

#### 成功响应
```json
{
  "code": 200,
  "message": "任务创建成功",
  "data": {
    "taskNo": "R20250715145830999",
    "taskType": "INBOUND",
    "status": "PENDING",
    "createTime": "2025-01-15 14:58:30"
  }
}
```

#### 失败响应
```json
{
  "code": 400,
  "message": "库位已被占用，无法创建入库任务",
  "data": null
}
```

### 任务编号规则

- **入库任务**：`R` + 日期时间（精确到毫秒），例如：`R20250715145830999`
- **送检任务**：`S` + 日期时间（精确到毫秒），例如：`S20250715145830999`
- **回库任务**：`H` + 日期时间（精确到毫秒），例如：`H20250715145830999`
- **出库任务**：`C` + 日期时间（精确到毫秒），例如：`C20250715145830999`

### 站点编码说明

| 站点类型 | 编码格式 | 示例 |
|----------|----------|------|
| 库前置换区 | WAREHOUSE_SWAP_{站点号} | WAREHOUSE_SWAP_1 |
| 库位 | WAREHOUSE_LOCATION_{库位号} | WAREHOUSE_LOCATION_2_01 |
| 检测区站点 | INSPECTION_STATION | INSPECTION_STATION |
| 接驳位 | TRANSFER_STATION | TRANSFER_STATION |

---

## 5. 查询任务接口

### 接口说明
根据日期范围查询任务列表。

### 请求信息

- **请求方法**：`POST`
- **请求路径**：`/api/task/query`
- **请求头**：`Content-Type: application/json`

### 请求参数

| 参数名 | 类型 | 是否必填 | 说明 | 示例值 |
|--------|------|----------|------|--------|
| startDate | String | 否 | 起始日期，格式：yyyy-MM-dd。如果为空，默认查询当天 | "2025-01-15" |
| endDate | String | 否 | 结束日期，格式：yyyy-MM-dd。如果为空，默认查询当天 | "2025-01-15" |
| taskType | String | 否 | 任务类型筛选：INBOUND / SEND_INSPECTION / RETURN / OUTBOUND | "INBOUND" |
| status | String | 否 | 任务状态筛选：PENDING / EXECUTING / COMPLETED / CANCELLED / FAILED | "PENDING" |
| pageNum | Integer | 否 | 页码，从1开始，默认1 | 1 |
| pageSize | Integer | 否 | 每页数量，默认20 | 20 |

### 请求示例

```json
{
  "startDate": "2025-01-15",
  "endDate": "2025-01-15",
  "taskType": "INBOUND",
  "status": "PENDING",
  "pageNum": 1,
  "pageSize": 20
}
```

### 响应参数

| 参数名 | 类型 | 说明 | 示例值 |
|--------|------|------|--------|
| list | Array | 任务列表 | - |
| list[].taskNo | String | 任务编号 | "R20250715145830999" |
| list[].taskType | String | 任务类型 | "INBOUND" |
| list[].status | String | 任务状态 | "PENDING" |
| list[].createTime | String | 创建时间 | "2025-01-15 14:58:30" |
| list[].palletNo | String | 托盘号 | "11-01" |
| list[].valveNo | String | 阀门编号 | "V20250101-001" |
| list[].locationCode | String | 库位号 | "2-01" |
| total | Integer | 总记录数 | 10 |
| pageNum | Integer | 当前页码 | 1 |
| pageSize | Integer | 每页数量 | 20 |

### 响应示例

#### 成功响应
```json
{
  "code": 200,
  "message": "查询成功",
  "data": {
    "list": [
      {
        "taskNo": "R20250715145830999",
        "taskType": "INBOUND",
        "status": "PENDING",
        "createTime": "2025-01-15 14:58:30",
        "palletNo": "11-01",
        "valveNo": "V20250101-001",
        "locationCode": "2-01"
      },
      {
        "taskNo": "S20250715145930999",
        "taskType": "SEND_INSPECTION",
        "status": "EXECUTING",
        "createTime": "2025-01-15 14:59:30",
        "palletNo": "11-02",
        "valveNo": "V20250101-002",
        "locationCode": "2-02"
      }
    ],
    "total": 2,
    "pageNum": 1,
    "pageSize": 20
  }
}
```

---

## 6. 取消任务接口

### 接口说明
取消指定任务，只能取消状态为"待执行"的任务。

### 请求信息

- **请求方法**：`POST`
- **请求路径**：`/api/task/cancel`
- **请求头**：`Content-Type: application/json`

### 请求参数

| 参数名 | 类型 | 是否必填 | 说明 | 示例值 |
|--------|------|----------|------|--------|
| taskNo | String | 是 | 任务编号 | "R20250715145830999" |
| operator | String | 否 | 操作员 | "张三" |

### 请求示例

```json
{
  "taskNo": "R20250715145830999",
  "operator": "张三"
}
```

### 响应参数

| 参数名 | 类型 | 说明 | 示例值 |
|--------|------|------|--------|
| success | Boolean | 是否成功 | true |

### 响应示例

#### 成功响应
```json
{
  "code": 200,
  "message": "任务取消成功",
  "data": {
    "success": true
  }
}
```

#### 失败响应
```json
{
  "code": 400,
  "message": "任务状态不允许取消，只能取消待执行的任务",
  "data": {
    "success": false
  }
}
```

---

## 数据模型定义

### 托盘信息（Pallet）

```json
{
  "palletNo": "String, 托盘编号",
  "palletType": "String, 托盘型号：SMALL/LARGE",
  "swapStation": "String, 置换区站点",
  "locationCode": "String, 库位号"
}
```

### 阀门信息（Valve）

```json
{
  "valveNo": "String, 阀门编号",
  "valveModel": "String, 阀门型号",
  "vendorName": "String, 厂家名称",
  "inboundDate": "String, 入库日期（yyyy-MM-dd）",
  "palletNo": "String, 托盘号",
  "locationCode": "String, 库位号",
  "valveStatus": "String, 阀门状态"
}
```

### 任务信息（Task）

```json
{
  "taskNo": "String, 任务编号",
  "taskType": "String, 任务类型",
  "status": "String, 任务状态",
  "createTime": "String, 创建时间",
  "palletNo": "String, 托盘号",
  "valveNo": "String, 阀门编号",
  "locationCode": "String, 库位号"
}
```

---

## 枚举值说明

### 托盘型号（palletType）
- `SMALL`：小托盘（1类托盘，共132个）
- `LARGE`：大托盘（2类托盘，共33个）

### 阀门状态（valveStatus）
- `IN_STOCK`：在库
- `IN_INSPECTION`：检测中
- `INSPECTED`：已检测
- `OUTBOUND`：已出库

### 任务类型（taskType）
- `INBOUND`：入库
- `SEND_INSPECTION`：送检
- `RETURN`：回库
- `OUTBOUND`：出库

### 任务状态（status）
- `PENDING`：待执行
- `EXECUTING`：执行中
- `COMPLETED`：已完成
- `CANCELLED`：已取消
- `FAILED`：失败

---

## 错误码说明

| 错误码 | 说明 | 处理建议 |
|--------|------|----------|
| 200 | 操作成功 | - |
| 400 | 请求参数错误 | 检查请求参数是否正确 |
| 401 | 未授权 | 检查认证信息 |
| 403 | 禁止访问 | 检查权限 |
| 404 | 资源不存在 | 检查资源ID是否正确 |
| 500 | 服务器内部错误 | 联系技术支持 |

### 业务错误码（建议）

| 错误码 | 说明 |
|--------|------|
| 1001 | 托盘不存在 |
| 1002 | 托盘已被占用 |
| 1003 | 阀门编号已存在 |
| 1004 | 库位已被占用 |
| 1005 | 任务不存在 |
| 1006 | 任务状态不允许此操作 |
| 1007 | 阀门不存在 |
| 1008 | 阀门状态不允许此操作 |

---

## 接口调用流程示例

### 入库流程

1. **托盘扫码**
   ```
   POST /api/pallet/scan
   Request: {"barcode": "PALLET-QR-CODE-11-01"}
   Response: {"palletNo": "11-01", "palletType": "SMALL", ...}
   ```

2. **阀门绑定**
   ```
   POST /api/valve/bind
   Request: {"valveNo": "V20250101-001", "valveModel": "DN50", ...}
   Response: {"success": true}
   ```

3. **创建入库任务**
   ```
   POST /api/task/create
   Request: {"taskType": "INBOUND", "palletNo": "11-01", ...}
   Response: {"taskNo": "R20250715145830999", ...}
   ```

### 送检流程

1. **查询阀门**
   ```
   POST /api/valve/query
   Request: {"vendorName": "XX阀门厂", "valveStatus": "IN_STOCK"}
   Response: {"list": [...], "total": 10}
   ```

2. **创建送检任务**
   ```
   POST /api/task/create
   Request: {"taskType": "SEND_INSPECTION", "palletNo": "11-01", ...}
   Response: {"taskNo": "S20250715145830999", ...}
   ```

### 任务管理流程

1. **查询任务**
   ```
   POST /api/task/query
   Request: {"startDate": "2025-01-15", "endDate": "2025-01-15"}
   Response: {"list": [...], "total": 10}
   ```

2. **取消任务**
   ```
   POST /api/task/cancel
   Request: {"taskNo": "R20250715145830999"}
   Response: {"success": true}
   ```

---

## 注意事项

1. **时间格式**：所有日期时间字段统一使用格式：
   - 日期：`yyyy-MM-dd`，例如：`2025-01-15`
   - 日期时间：`yyyy-MM-dd HH:mm:ss`，例如：`2025-01-15 14:58:30`

2. **字符编码**：所有请求和响应使用UTF-8编码

3. **网络超时**：建议设置请求超时时间为10秒

4. **重试机制**：网络请求失败时，建议实现重试机制（最多重试3次）

5. **错误处理**：所有接口调用都需要进行错误处理，向用户显示友好的错误提示

6. **数据校验**：PDA端需要对输入数据进行校验，减少无效请求

7. **日志记录**：建议记录所有API调用日志，便于问题排查

8. **接口版本**：如果后续需要升级接口，建议在URL中添加版本号，如：`/api/v1/pallet/scan`

---

## 版本历史

- **v1.0.0** (2025-01-15)：初始版本，包含6个核心API接口

