package com.xiaoying.imapi.model;

import android.util.Log;

/**
 * Created by xiyoumc on 16/7/26.
 */
public enum ErrorCode {
    PARAMETER_ERROR(-3, "the parameter is error."),
    IPC_DISCONNECT(-2, "IPC is not connected"),
    UNKNOWN(-1, "unknown"),
    CONNECTED(0, "connected"),
    MSG_ROAMING_SERVICE_UNAVAILABLE('胯', "Message roaming service unavailable"),
    NOT_IN_DISCUSSION(21406, ""),
    NOT_IN_GROUP(22406, ""),
    FORBIDDEN_IN_GROUP(22408, ""),
    NOT_IN_CHATROOM(23406, ""),
    FORBIDDEN_IN_CHATROOM(23408, ""),
    KICKED_FROM_CHATROOM(23409, ""),
    RC_CHATROOM_NOT_EXIST(23410, "Chat room does not exist"),
    RC_CHATROOM_IS_FULL(23411, "Chat room is full"),
    RC_CHATROOM_ILLEGAL_ARGUMENT(23412, "illegal argument."),
    REJECTED_BY_BLACKLIST(405, "rejected by blacklist"),
    RC_NET_CHANNEL_INVALID(30001, "Socket does not exist"),
    RC_NET_UNAVAILABLE(30002, ""),
    RC_MSG_RESP_TIMEOUT(30003, ""),
    RC_HTTP_SEND_FAIL(30004, ""),
    RC_HTTP_REQ_TIMEOUT(30005, ""),
    RC_HTTP_RECV_FAIL(30006, ""),
    RC_NAVI_RESOURCE_ERROR(30007, ""),
    RC_NODE_NOT_FOUND(30008, ""),
    RC_DOMAIN_NOT_RESOLVE(30009, ""),
    RC_SOCKET_NOT_CREATED(30010, ""),
    RC_SOCKET_DISCONNECTED(30011, ""),
    RC_PING_SEND_FAIL(30012, ""),
    RC_PONG_RECV_FAIL(30013, ""),
    RC_MSG_SEND_FAIL(30014, ""),
    RC_CONN_ACK_TIMEOUT(31000, ""),
    RC_CONN_PROTO_VERSION_ERROR(31001, ""),
    RC_CONN_ID_REJECT(31002, ""),
    RC_CONN_SERVER_UNAVAILABLE(31003, ""),
    RC_CONN_USER_OR_PASSWD_ERROR(31004, ""),
    RC_CONN_NOT_AUTHRORIZED(31005, ""),
    RC_CONN_REDIRECTED(31006, ""),
    RC_CONN_PACKAGE_NAME_INVALID(31007, ""),
    RC_CONN_APP_BLOCKED_OR_DELETED(31008, ""),
    RC_CONN_USER_BLOCKED(31009, ""),
    RC_DISCONN_KICK(31010, ""),
    RC_DISCONN_EXCEPTION(31011, ""),
    RC_QUERY_ACK_NO_DATA(32001, ""),
    RC_MSG_DATA_INCOMPLETE(32002, ""),
    BIZ_ERROR_CLIENT_NOT_INIT('胩', ""),
    BIZ_ERROR_DATABASE_ERROR('胪', ""),
    BIZ_ERROR_INVALID_PARAMETER('胫', ""),
    BIZ_ERROR_NO_CHANNEL('胬', ""),
    BIZ_ERROR_RECONNECT_SUCCESS('胭', ""),
    BIZ_ERROR_CONNECTING('胮', ""),
    NOT_FOLLOWED(29106, "");

    private int code;
    private String msg;

    private ErrorCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getValue() {
        return this.code;
    }

    public String getMessage() {
        return this.msg;
    }

    public static ErrorCode valueOf(int code) {
        ErrorCode[] var1 = values();
        int var2 = var1.length;

        for (int var3 = 0; var3 < var2; ++var3) {
            ErrorCode c = var1[var3];
            if (code == c.getValue()) {
                return c;
            }
        }

        Log.d("RongIMClient", "valueOf,ErrorCode:" + code);
        return UNKNOWN;
    }
}
