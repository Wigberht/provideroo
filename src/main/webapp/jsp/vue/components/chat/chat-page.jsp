<script type="text/x-template" id="chat-page-template">

    <div class="chat-page">
        <chat-list v-if="page=='chats'"
                   @toChat="toChat(...arguments)"
                   @toUserList="toUserList"
        />

        <user-list v-if="page=='users'"
                   @toChat="toChat(...arguments)"/>

        <chat-dialogue
            v-if="page=='dialogue'"
            :chat_id="dialogue_id"
        />
    </div>
</script>

<script>
    Vue.component('chat-page', {
        template: "#chat-page-template",
        data() {
            return {
                user: window.user,
                pageTitle: strings['list_of_chats'],
                page: "chats",
                dialogue_id: 0
            }
        },
        methods: {
            toChat(args) {
                console.log("args in chat page: ", args);
                this.dialogue_id = args;
                this.setActivePage("dialogue");
            },

            toUserList() {
                this.setActivePage("users");
            },
            setActivePage(page) {
                this.page = page;
            }
        },
        mounted: function () {
            console.log("chat page mounted");
        },
        computed: {}
    })
</script>
