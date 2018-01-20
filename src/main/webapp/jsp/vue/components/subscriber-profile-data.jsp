<script type="text/x-template" id="ban-button-template">
    <div class="row">
        <div class="input-field col s6">
            <input placeholder="Placeholder"
                   id="first_name"
                   type="text"
                   class="validate">
            <label for="first_name">
                <fmt:message key="first_name"/>
            </label>
        </div>
    </div>
    <div class="row">
        <div class="input-field col s6">
            <input placeholder="Placeholder"
                   id="last_name"
                   type="text"
                   class="validate">
            <label for="last_name">
                <fmt:message key="last_name"/>
            </label>
        </div>
    </div>

    <div class="row">
        <div class="input-field col s6">
            <input placeholder="Placeholder"
                   id="login"
                   type="text"
                   class="validate"
                   value="">
            <label for="login">
                <fmt:message key="login"/>
            </label>
        </div>
    </div>
</script>

<script>
    Vue.component('ban-button', {
        props: ['text_login', 'text_first_name', 'text_last_name', 'user_id'],
        template: "#ban-button-template",
        data() {
            return {
                banned: false,
                toast: {
                    time: 1000,
                }
            }
        },
        methods: {
            banToggle() {
                axios.post("/rest/user/ban", {
                    'userId': this.user_id,
                    'banned': !this.banned
                }).then((response) => {
                    console.log(response);

                    if (response.data.success) {
                        this.banned = !this.banned;
                        Materialize.toast("Toggle success", this.toast.time)
                    } else {
                        Materialize.toast("Toggle fail", this.toast.time)
                    }
                }).catch((error) => {
                    console.log(error);
                    Materialize.toast("Toggle fail", this.toast.time);
                })
            },
        },
        mounted: function () {
            this.banned = this.banned_state == 'true';
            console.log("Banned: ", this.banned);
        },
        computed: {
            buttonText() {
                return this.banned ? this.text_unban : this.text_ban;
            }
        }
    })
</script>
