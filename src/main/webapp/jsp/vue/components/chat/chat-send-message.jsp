<script type="text/x-template" id="chat-send-message-template">
    <div class="row">
        <div class="col s6 offset-s2">
            <input @keyup.enter="send" type="text" v-model="message">
        </div>
        <div class="col s2">
            <a @click="send" class="waves-effect waves-light btn">SEND</a>
        </div>
    </div>
</script>

<script>
    Vue.component('chat-send-message', {
        template: "#chat-send-message-template",
        data() {
            return {
                message: "",
            }
        },
        methods: {
            send() {
                const message = {
                    message: this.message,
                    userId: window.user.id
                };
                this.$emit('send', message);
                this.message = "";
            },
        },
        mounted: function () {
        },
    })
</script>