<script type="text/x-template" id="chat-message-template">
    <div>
        <template v-if="isMine">
            <div class="right message-right message-block word-wrap">
                <p>{{message.message}}</p>
                <p class="message-under-time">{{message.createdAt}}</p>
            </div>
        </template>

        <template v-if="!isMine">
            <div class="left message-left message-block word-wrap">
                <p><b>{{messageAuthor.login}}</b> : {{message.message}}</p>
                <p class="message-under-time">{{message.createdAt}}</p>
            </div>
        </template>
    </div>
</script>

<script>
    Vue.component('chat-message', {
        props: ['user_id', 'message', 'users'],
        template: "#chat-message-template",
        data() {
            return {
                isMine: this.message.userId == window.user.id,
                messageAuthor: ""
            }
        },
        methods: {
            getMessageUser() {
                return this.users.find((messageUser) => {
                    return messageUser.id == this.message.userId;
                });
            }
        },
        mounted: function () {
            this.messageAuthor = this.getMessageUser();
        },
        computed: {}
    })
</script>
